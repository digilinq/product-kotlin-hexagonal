package org.digilinq.retail.domain.product.ports

import org.digilinq.retail.domain.product.model.ProductDto

interface ProductRepositoryPort {
    fun findAll(): List<ProductDto>
}