package org.digilinq.retail.infrastructure.config

import org.digilinq.retail.domain.product.ProductService
import org.digilinq.retail.domain.product.ports.ProductRepositoryPort
import org.digilinq.retail.domain.product.service.ProductServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductConfiguration(
    val productRepositoryPort: ProductRepositoryPort
) {
    @Bean
    fun productService(): ProductService = ProductServiceImpl(productRepositoryPort)
}