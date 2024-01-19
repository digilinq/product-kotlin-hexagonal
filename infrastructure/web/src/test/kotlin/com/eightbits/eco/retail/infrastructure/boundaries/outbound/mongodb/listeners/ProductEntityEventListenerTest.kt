package com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.listeners

import com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.entities.ProductEntity
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ProductEntityEventListenerTest {
    private val productEntityEventListener = ProductEntityEventListener()

    @Test
    fun `should generate product id if product id is null`() {
        val product = ProductEntity(name = "Computer", description = "Gaming computer")
        productEntityEventListener.generateProductId(product)
        assertNotNull(product.productId)
    }
}