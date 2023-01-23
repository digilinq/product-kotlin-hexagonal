package org.digilinq.retail.infrastructure.mapper

import org.digilinq.ecommerce.product.generated.v1.model.Product
import org.digilinq.retail.domain.product.model.ProductDto
import org.digilinq.retail.infrastructure.model.ProductEntity

interface ProductMapper {
    fun map(product: Product): ProductDto
    fun map(productDto: ProductDto): Product
    fun map(productDtos: List<ProductDto>): List<Product>
    fun map1(productEntities: List<ProductEntity>): List<ProductDto>
    fun map(productEntity: ProductEntity): ProductDto
}