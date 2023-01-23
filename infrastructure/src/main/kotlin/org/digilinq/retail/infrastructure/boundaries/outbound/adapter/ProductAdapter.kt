package org.digilinq.retail.infrastructure.boundaries.outbound.adapter

import org.digilinq.retail.domain.product.model.ProductDto
import org.digilinq.retail.domain.product.ports.ProductRepositoryPort
import org.digilinq.retail.infrastructure.boundaries.outbound.repository.ProductRepository
import org.digilinq.retail.infrastructure.mapper.ProductMapper
import org.springframework.stereotype.Component

@Component
class ProductAdapter(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
) : ProductRepositoryPort {
    override fun findAll(): List<ProductDto> {
        val productDtos = productRepository.findAll().let {
            productMapper.map1(it)
        }
        return productDtos
    }
}