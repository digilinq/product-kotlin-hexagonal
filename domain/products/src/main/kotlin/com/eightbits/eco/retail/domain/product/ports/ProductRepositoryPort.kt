package com.eightbits.eco.retail.domain.product.ports

import com.eightbits.eco.retail.domain.product.model.Product

interface ProductRepositoryPort {
    fun findAll(): List<Product>
}