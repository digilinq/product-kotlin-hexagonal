package com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.listeners

import com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.entities.ProductEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductEntityEventListener : AbstractMongoEventListener<ProductEntity>() {
    private val logger: Logger = LoggerFactory.getLogger(ProductEntityEventListener::class.java)
    override fun onBeforeConvert(event: BeforeConvertEvent<ProductEntity>) {
        super.onBeforeConvert(event)
        generateProductId(event.source)
    }

    fun generateProductId(product: ProductEntity) = product.productId ?: run {
        logger.info("Generating product id ...")
        product.productId = UUID.randomUUID()
    }
}