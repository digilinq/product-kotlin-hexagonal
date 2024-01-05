package com.eightbits.eco.retail.domain.product.model

import java.util.*

data class Product(
    val id: UUID,
    val name: String,
    val description: String,
)
