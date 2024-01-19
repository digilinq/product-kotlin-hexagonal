package com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document("products")
data class ProductEntity(
    val name: String,
    val description: String
) {
    @Id @Field("_id")
    var id: String? = null

    @Field("productId")
    @Indexed
    var productId: UUID? = null
}
