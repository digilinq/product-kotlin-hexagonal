package com.eightbits.eco.retail.infrastructure.web.resources

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.infrastructure.configuration.JacksonConfiguration
import com.eightbits.eco.retail.infrastructure.integration.mockdata.Products
import com.eightbits.eco.retail.infrastructure.web.mappings.ProductMapperImpl
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.hamcrest.core.IsIterableContaining.hasItems
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.util.*

@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureWebMvc
//@MockBeans(
//    MockBean(ProductService::class),
//)
@Import(ProductMapperImpl::class, JacksonConfiguration::class)
class ProductsResourceTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val applicationContext: ApplicationContext,
    private val objectMapper: ObjectMapper,
    private val productService: ProductService
) {
    @TestConfiguration
    class SpringContextConfiguration {
        @Bean
        fun service() = mockk<ProductService>()
    }

    @Test
    fun `should contains products GET and POST endpoints`() {
        val requestMappingHandlerMapping =
            applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping::class.java)
        val map = requestMappingHandlerMapping.handlerMethods
        map.forEach { (key: RequestMappingInfo?, value: HandlerMethod?) ->
            println("$key $value")
        }
    }

    @Test
    fun `should return location as a header on save product`() {
        val product = Product(
            name = PRODUCT_NAME,
            description = PRODUCT_DESCRIPTION
        ).let {
            it.id = PRODUCT_ID
            it
        }

        every { productService.save(any()) } returns product

        mockMvc.post(ENDPOINT_PRODUCTS) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(product)
        }.andDo { print() }.andExpect {
            status { isCreated() }
            header {
                stringValues("Location", hasItems(containsString("$ENDPOINT_PRODUCTS/$PRODUCT_ID")))
            }
        }
    }

    @Test
    fun `should validate product for mandatory fields`() {
        mockMvc.post(ENDPOINT_PRODUCTS) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = Products.INVALID_PRODUCT.string
        }.andDo { print() }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `validate product`(@Autowired validator: Validator) {
        val violations: Set<ConstraintViolation<com.eightbits.eco.retail.infrastructure.generated.v1.model.Product>> =
            validator.validate(Products.INVALID_PRODUCT.instance)

        val exception = assertThrows<ConstraintViolationException> {
            if (violations.isNotEmpty()) {
                throw ConstraintViolationException(violations)
            }
        }

        exception.constraintViolations.forEach {
            violation -> println("${violation.propertyPath}: ${violation.invalidValue} is not valid, ${violation.message}")
        }
    }

    @Test
    fun `bean validation`(@Autowired validator: Validator) {
        assertNotNull(validator)
    }

    companion object {
        val PRODUCT_ID: UUID = UUID.fromString("0a2e995b-f337-475b-a301-75cc8ee593e9")
        const val PRODUCT_NAME = "Computer"
        const val PRODUCT_DESCRIPTION = "Gaming computer"
        const val ENDPOINT_PRODUCTS = "/api/v1/products"
    }
}