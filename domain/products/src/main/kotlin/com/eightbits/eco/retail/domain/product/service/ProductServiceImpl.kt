package com.eightbits.eco.retail.domain.product.service

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.domain.product.model.ProductDto
import com.eightbits.eco.retail.domain.product.ports.ProductRepositoryPort

class ProductServiceImpl(
    private val productRepositoryPort: ProductRepositoryPort
) : ProductService {
    override fun findAll(): List<ProductDto> {
        return productRepositoryPort.findAll()
    }
}