package org.digilinq.retail.domain.product

import org.digilinq.retail.domain.product.model.ProductDto

interface ProductService {
    fun findAll(): List<ProductDto>
}