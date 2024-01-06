package com.eightbits.eco.retail.infrastructure.boundaries.inbound.rest

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.infrastructure.generated.v1.api.ProductsApi
import com.eightbits.eco.retail.infrastructure.generated.v1.model.Product
import com.eightbits.eco.retail.infrastructure.mapper.ProductMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

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

    override fun saveProduct(product: Product): ResponseEntity<Unit> {
        val location: URI =
            productService.save(productMapper.map(product))
                .let { p ->
                    ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}").buildAndExpand(p.id).encode()
                        .toUri()
                }

        return ResponseEntity.created(location).build()
    }
}