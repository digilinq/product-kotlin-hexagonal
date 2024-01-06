package com.eightbits.eco.retail.infrastructure.boundaries.outbound.repository

import com.eightbits.eco.retail.infrastructure.model.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<ProductEntity, String>