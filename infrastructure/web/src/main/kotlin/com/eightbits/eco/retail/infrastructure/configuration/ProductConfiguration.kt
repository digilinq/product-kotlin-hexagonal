package com.eightbits.eco.retail.infrastructure.configuration

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.domain.product.ports.ProductRepositoryPort
import com.eightbits.eco.retail.domain.product.service.ProductServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductConfiguration(
    val productRepositoryPort: ProductRepositoryPort
) {
    @Bean
    fun productService(): ProductService = ProductServiceImpl(productRepositoryPort)
}