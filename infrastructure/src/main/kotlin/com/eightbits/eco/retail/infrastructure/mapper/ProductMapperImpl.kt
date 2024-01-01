package com.eightbits.eco.retail.infrastructure.mapper

import com.eightbits.eco.retail.infrastructure.generated.v1.model.Product
import com.eightbits.eco.retail.domain.product.model.ProductDto
import com.eightbits.eco.retail.infrastructure.model.ProductEntity
import org.springframework.stereotype.Component

@Component
class ProductMapperImpl : ProductMapper {
    override fun map(product: Product): ProductDto {
        return ProductDto(
            product.name
        )
    }

    override fun map(productDto: ProductDto): Product {
        return Product(name = productDto.name)
    }

    override fun map(productDtos: List<ProductDto>): List<Product> {
        return productDtos.map {
            map(it)
        }
    }

    override fun map1(productEntities: List<ProductEntity>): List<ProductDto> {
        val productDtos = productEntities.map {
            map(it)
        }
        return productDtos
    }

    override fun map(productEntity: ProductEntity): ProductDto {
        return ProductDto(productEntity.name)
    }
}