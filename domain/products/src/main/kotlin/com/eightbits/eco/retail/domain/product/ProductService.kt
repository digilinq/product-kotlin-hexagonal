package com.eightbits.eco.retail.domain.product

import com.eightbits.eco.retail.domain.product.model.Product

interface ProductService {
    fun findAll(): List<Product>
    fun save(product:Product):Product
}