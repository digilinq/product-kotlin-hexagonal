package org.digilinq.retail.infrastructure.boundaries.outbound.repository

import org.digilinq.retail.infrastructure.model.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<ProductEntity, String>