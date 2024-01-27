package com.eightbits.eco.retail.infrastructure.web.resources

import com.eightbits.eco.retail.infrastructure.IntegrationTest
import com.eightbits.eco.retail.infrastructure.integration.TestUris
import com.eightbits.eco.retail.infrastructure.integration.mockdata.Products
import com.eightbits.eco.retail.infrastructure.mongodb.entities.ProductEntity
import com.eightbits.eco.retail.infrastructure.mongodb.repository.ProductRepository
import com.eightbits.eco.retail.infrastructure.web.resources.ProductsResourceIT.Companion.LOG_LEVEL
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.AbstractEnvironment
import org.springframework.core.env.Environment
import org.springframework.core.env.MapPropertySource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@IntegrationTest
@SpringBootTest(
    properties = [
        "logging.level.root=$LOG_LEVEL"
    ]
)
class ProductsResourceIT @Autowired constructor(
    private val mockMvc: MockMvc,
    private val productRepository: ProductRepository,
    private val env: Environment
) {

    @Test
    fun `log level should be warning`() {
        val propertySources = (env as AbstractEnvironment).propertySources
        propertySources.forEach { propertySource ->
            println(">>> ${propertySource.name}")
            if (propertySource is MapPropertySource)
                propertySource.propertyNames.forEach { propertyName -> println(propertyName) }
        }

        Assertions.assertEquals("WARN", env.getProperty("logging.level.root"))
    }

    @Test
    fun `should fetch all products`() {
        val product = productRepository.save(ProductEntity("Computer", "Gaming computer"))

        mockMvc.get(TestUris.ENDPOINT_PRODUCTS).andDo { print() }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                jsonPath("$[*].id") {
                    value(hasItems(product.productId.toString()))
                }
                jsonPath("$[*].name") {
                    value(hasItems("Computer"))
                }
                jsonPath("$[*].description") {
                    value(hasItems("Gaming computer"))
                }
            }
        }
    }

    @Test
    fun `should persist product in mongodb and productId should be generated`() {
        val result = mockMvc.post(TestUris.ENDPOINT_PRODUCTS) {
            // header("API-Key", TEST_API_KEY)
            contentType = MediaType.APPLICATION_JSON
            content = Products.PRODUCT_CREATE.string
            accept = MediaType.APPLICATION_JSON
        }.andDo { print() }.andExpect {
            status { isCreated() }
        }.andReturn()

        assertThat(result.response.headerNames).contains(HEADER_LOCATION)
        assertThat(result.response.getHeaders(HEADER_LOCATION)).isNotEmpty

        assertThat(productRepository.findAll()).hasSize(1)
        assertThat(productRepository.findAll()).allMatch { it.productId != null }
    }

    @Test
    fun `should fetch by productId`() {
        val product = productRepository.save(ProductEntity("Computer", "Gaming computer"))

        mockMvc.get(TestUris.ENDPOINT_PRODUCTS_ID, product.productId).andDo { print() }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                jsonPath("id") {
                    value(product.productId.toString())
                }
                jsonPath("name") {
                    value("Computer")
                }
                jsonPath("description") {
                    value("Gaming computer")
                }
            }
        }
    }

    companion object {
        const val HEADER_LOCATION = "Location"
        const val LOG_LEVEL = "WARN"
    }
}