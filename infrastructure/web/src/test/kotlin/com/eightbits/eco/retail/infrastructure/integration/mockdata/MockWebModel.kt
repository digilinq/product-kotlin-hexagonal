package com.eightbits.eco.retail.infrastructure.integration.mockdata

import com.eightbits.eco.retail.infrastructure.common.utils.ResourceApiModel
import com.eightbits.eco.retail.infrastructure.generated.v1.model.Product

enum class Products(override val path: String) : ResourceApiModel<Product> {
    PRODUCT_CREATE("mock-data/products/product.json"),
    ;

    override val type = Product::class.java
}