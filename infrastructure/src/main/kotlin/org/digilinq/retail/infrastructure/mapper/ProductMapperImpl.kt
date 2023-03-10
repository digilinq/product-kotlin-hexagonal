package org.digilinq.retail.infrastructure.mapper

import org.digilinq.ecommerce.product.generated.v1.model.Product
import org.digilinq.retail.domain.product.model.ProductDto
import org.digilinq.retail.infrastructure.model.ProductEntity
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