package com.eightbits.eco.retail.domain.product.ports

import com.eightbits.eco.retail.domain.product.model.ProductDto

interface ProductRepositoryPort {
    fun findAll(): List<ProductDto>
}