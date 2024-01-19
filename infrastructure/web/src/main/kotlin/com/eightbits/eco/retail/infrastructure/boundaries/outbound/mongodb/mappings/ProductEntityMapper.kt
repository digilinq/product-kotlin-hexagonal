package com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.mappings

import com.eightbits.eco.retail.domain.product.model.Product
import com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.entities.ProductEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface ProductEntityMapper {

    @Mapping(target = "id", source = "productId")
    fun map(product: ProductEntity): Product

    fun map(products: MutableList<ProductEntity>): MutableList<Product>

    @Mapping(target = "productId", source = "id")
    fun map(product: Product): ProductEntity
}
