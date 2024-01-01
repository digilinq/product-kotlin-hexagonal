package com.eightbits.eco.retail.infrastructure.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ProductEntity(
    @Id val id: String,
    val name: String
)
