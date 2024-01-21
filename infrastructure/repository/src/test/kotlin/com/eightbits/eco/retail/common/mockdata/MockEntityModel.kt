package com.eightbits.eco.retail.common.mockdata

import com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.entities.ProductEntity
import com.eightbits.eco.retail.infrastructure.common.utils.ResourceApiModel

class MockEntityModel {
    enum class ProductEntityExamples(override val path: String) : ResourceApiModel<ProductEntity> {
        PRODUCT_WITH_EMPTY_ID("mock-data/mongodb/products/product-with-empty-id.json"),
        ;

        override val type = ProductEntity::class.java
    }
}
