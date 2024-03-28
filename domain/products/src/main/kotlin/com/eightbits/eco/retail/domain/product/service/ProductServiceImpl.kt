package com.eightbits.eco.retail.domain.product.service

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.domain.product.ports.ProductRepositoryPort
import java.util.*

class ProductServiceImpl(
    private val productRepositoryPort: ProductRepositoryPort
) : ProductService {

    override fun findAll(): List<Product> {
        return productRepositoryPort.findAll()
    }

    override fun save(product: Product): Product {
        return productRepositoryPort.save(product)
    }

    override fun remove(id: UUID) {
        productRepositoryPort.removeByProductId(id)
    }

    override fun findById(id: UUID): Product {
        return productRepositoryPort.findByProductId(id)
    }
}