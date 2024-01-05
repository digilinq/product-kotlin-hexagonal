package com.eightbits.eco.retail.infrastructure.mapper

import com.eightbits.eco.retail.infrastructure.generated.v1.model.Product as ProductWebModel
import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.infrastructure.model.ProductEntity
import org.mapstruct.Mapper

@Mapper
interface ProductMapper {
    fun map(product: ProductWebModel): Product
    fun map(productDto: Product): ProductWebModel
    fun map(productDtos: List<Product>): List<ProductWebModel>
    fun map1(productEntities: List<ProductEntity>): List<Product>
    fun map(productEntity: ProductEntity): Product
}