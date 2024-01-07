package com.eightbits.eco.retail.domain.product

import com.eightbits.eco.retail.domain.product.model.Product
import java.util.*

interface ProductService {
    fun findById(id: String):Product
    fun findAll(): List<Product>
    fun save(product:Product):Product
}