package com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class ProductEntity(
    @Id val id: String?,
    val name: String,
    val description: String
)
