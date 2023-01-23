package org.digilinq.retail.infrastructure.boundaries.inbound.rest

import org.digilinq.ecommerce.product.generated.v1.api.ProductsApi
import org.digilinq.ecommerce.product.generated.v1.model.Product
import org.digilinq.retail.domain.product.ProductService
import org.digilinq.retail.infrastructure.mapper.ProductMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class ProductsResource(
    private val productService: ProductService,
    private val productMapper: ProductMapper
) : ProductsApi {

    override fun findProducts(productName: String?): ResponseEntity<List<Product>> {
        val products = productService.findAll().let {
            productMapper.map(it)
        }
        return ResponseEntity.ok(products)
    }
}