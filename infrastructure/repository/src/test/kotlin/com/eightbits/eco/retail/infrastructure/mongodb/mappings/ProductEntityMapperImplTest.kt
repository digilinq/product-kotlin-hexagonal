package com.eightbits.eco.retail.infrastructure.mongodb.mappings

import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.infrastructure.mongodb.entities.ProductEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ProductEntityMapperImpl::class])
class ProductEntityMapperImplTest @Autowired constructor(
        private val productEntityMapper: ProductEntityMapper
) {
    @Test
    fun `should map product to product entity`() {
        val product = Product(name = PRODUCT_NAME, description = PRODUCT_DESCRIPTION).let {
            it.id = PRODUCT_ID
            it
        }

        val productEntity = productEntityMapper.map(product)
        assertEquals(PRODUCT_ID, productEntity.productId)
        assertEquals(PRODUCT_NAME, productEntity.name)
        assertEquals(PRODUCT_DESCRIPTION, productEntity.description)
    }

    @Test
    fun `should map product entity to product`() {
        val productEntity = ProductEntity(
                name = PRODUCT_NAME,
                description = PRODUCT_DESCRIPTION
        ).let {
            it.id = "659a6e2701513346391ffe34"
            it.productId = PRODUCT_ID
            it
        }

        val product = productEntityMapper.map(productEntity)
        assertEquals(PRODUCT_ID, product.id)
        assertEquals(PRODUCT_NAME, product.name)
        assertEquals(PRODUCT_DESCRIPTION, product.description)
    }

    companion object {
        private val PRODUCT_ID: UUID = UUID.fromString("e01e2990-7b70-40a4-841a-0ced8b5a782a")
        private const val PRODUCT_NAME = "Computer"
        private const val PRODUCT_DESCRIPTION = "Gaming computer"
    }
}