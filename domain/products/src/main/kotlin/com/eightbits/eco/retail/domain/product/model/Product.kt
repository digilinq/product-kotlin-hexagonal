package com.eightbits.eco.retail.domain.product.model

import java.util.*

data class Product(
        val name: String,
        val description: String,
) {
    var id: UUID? = null
}
