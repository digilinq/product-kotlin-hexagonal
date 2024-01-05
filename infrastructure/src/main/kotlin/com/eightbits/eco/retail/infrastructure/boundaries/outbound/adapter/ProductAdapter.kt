package com.eightbits.eco.retail.infrastructure.boundaries.outbound.adapter

import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.domain.product.ports.ProductRepositoryPort
import com.eightbits.eco.retail.infrastructure.boundaries.outbound.repository.ProductRepository
import com.eightbits.eco.retail.infrastructure.mapper.ProductMapper
import org.springframework.stereotype.Component

@Component
class ProductAdapter(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
) : ProductRepositoryPort {
    override fun findAll(): List<Product> {
        val productDtos = productRepository.findAll().let {
            productMapper.map1(it)
        }
        return productDtos
    }
}