package com.eightbits.eco.retail.infrastructure.boundaries.inbound.mappings

import com.eightbits.eco.retail.domain.product.model.Product
import org.mapstruct.Mapper
import com.eightbits.eco.retail.infrastructure.generated.v1.model.Product as ProductWebModel

@Mapper
interface ProductMapper {
    fun map(product: ProductWebModel): Product
    fun map(product: Product): ProductWebModel
    fun map(products: List<Product>): List<ProductWebModel>
}