package com.eightbits.eco.retail.infrastructure.mongodb.adapters

import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.domain.product.model.ProductNotFoundException
import com.eightbits.eco.retail.domain.product.ports.ProductRepositoryPort
import com.eightbits.eco.retail.infrastructure.mongodb.mappings.ProductEntityMapper
import com.eightbits.eco.retail.infrastructure.mongodb.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductRepositoryAdapter(
    private val repository: ProductRepository,
    private val mapper: ProductEntityMapper
) : ProductRepositoryPort {
    override fun findAll(): List<Product> {
        return repository.findAll().let {
            mapper.map(it)
        }
    }

    override fun findById(id: String): Product {
        return repository.findById(id).map(mapper::map).orElseThrow {
            ProductNotFoundException("Product not found for id: $id")
        }
    }

    override fun findByProductId(id: UUID): Product {
        return repository.findByProductId(id).map(mapper::map).orElseThrow {
            ProductNotFoundException("Product not found for id: $id")
        }
    }

    override fun save(product: Product): Product {
        return repository.save(mapper.map(product))
                .let(mapper::map)
    }
}