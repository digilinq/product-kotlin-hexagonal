package com.eightbits.eco.retail.infrastructure.mongodb.repository

import com.eightbits.eco.retail.infrastructure.mongodb.entities.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : MongoRepository<ProductEntity, String> {
    fun findByProductId(productId: UUID): Optional<ProductEntity>
    fun deleteByProductId(productId: UUID)
}