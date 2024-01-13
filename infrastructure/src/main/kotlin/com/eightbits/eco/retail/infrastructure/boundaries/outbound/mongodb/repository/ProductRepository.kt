package com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.repository

import com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.entities.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : MongoRepository<ProductEntity, String> {
    fun findByProductId(productId: UUID): Optional<ProductEntity>
}