package com.eightbits.eco.retail.infrastructure.mongodb.listeners

import com.eightbits.eco.retail.infrastructure.mongodb.entities.ProductEntity
import com.eightbits.eco.retail.infrastructure.mongodb.listeners.ProductEntityEventListener
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