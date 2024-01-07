package com.eightbits.eco.retail.domain.product.service

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.domain.product.ports.ProductRepositoryPort
import java.util.UUID

class ProductServiceImpl(
    private val productRepositoryPort: ProductRepositoryPort
) : ProductService {
    override fun findAll(): List<Product> {
        return productRepositoryPort.findAll()
    }

    override fun save(product: Product): Product {
        return Product(id = UUID.randomUUID(), name = product.name, description = product.description)
    }

    override fun findById(id: UUID): Product {
        return productRepositoryPort.findById(id)
    }
}