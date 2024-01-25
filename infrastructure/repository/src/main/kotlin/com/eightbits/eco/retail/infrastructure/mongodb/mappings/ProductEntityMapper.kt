package com.eightbits.eco.retail.infrastructure.mongodb.mappings

import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.infrastructure.mongodb.entities.ProductEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel

@Mapper(componentModel = ComponentModel.SPRING)
interface ProductEntityMapper {

    @Mapping(target = "id", source = "productId")
    fun map(product: ProductEntity): Product

    fun map(products: MutableList<ProductEntity>): MutableList<Product>

    @Mapping(target = "productId", source = "id")
    fun map(product: Product): ProductEntity
}
