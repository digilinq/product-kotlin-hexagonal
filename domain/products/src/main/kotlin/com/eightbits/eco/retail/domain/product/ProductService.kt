package com.eightbits.eco.retail.domain.product

import com.eightbits.eco.retail.domain.product.model.ProductDto

interface ProductService {
    fun findAll(): List<ProductDto>
}