package org.digilinq.retail.domain.product.service

import org.digilinq.retail.domain.product.ProductService
import org.digilinq.retail.domain.product.model.ProductDto
import org.digilinq.retail.domain.product.ports.ProductRepositoryPort

class ProductServiceImpl(
    private val productRepositoryPort: ProductRepositoryPort
) : ProductService {
    override fun findAll(): List<ProductDto> {
        return productRepositoryPort.findAll()
    }
}