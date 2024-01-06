package com.eightbits.eco.retail.infrastructure.boundaries.inbound.rest

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.infrastructure.config.JacksonConfiguration
import com.eightbits.eco.retail.infrastructure.mapper.ProductMapperImpl
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.core.IsIterableContaining.hasItems
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
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
            id = PRODUCT_ID,
            name = PRODUCT_NAME,
            description = PRODUCT_DESCRIPTION
        )

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

    companion object {
        val PRODUCT_ID: UUID = UUID.fromString("0a2e995b-f337-475b-a301-75cc8ee593e9")
        const val PRODUCT_NAME = "Computer"
        val PRODUCT_DESCRIPTION = "Gaming computer"
        val ENDPOINT_PRODUCTS = "/api/v1/products"
    }
}