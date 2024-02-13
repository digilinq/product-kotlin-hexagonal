package com.eightbits.eco.retail.infrastructure.web.resources

import com.eightbits.eco.retail.domain.product.ProductService
import com.eightbits.eco.retail.infrastructure.web.mappings.ProductMapper
import com.eightbits.eco.retail.infrastructure.generated.v1.api.ProductsApi
import com.eightbits.eco.retail.infrastructure.generated.v1.model.Product
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*

@RequestMapping("/api/v1")
@RestController
class ProductsResource(
    private val productService: ProductService,
    private val mapper: ProductMapper
) : ProductsApi {

    private val logger = LoggerFactory.getLogger(ProductsResource::class.java)

    @CrossOrigin(origins = ["*"])
    override fun findProducts(productName: String?): ResponseEntity<List<Product>> {

        logger.info("Find all products")

        val products = productService.findAll().let {
            mapper.map(it)
        }
        return ResponseEntity.ok(products)
    }

    override fun findProductById(id: UUID): ResponseEntity<Product> {
        return productService.findById(id).let(mapper::map).let {
            ResponseEntity.ok(it)
        }
    }

    @CrossOrigin(origins = ["*"])
    override fun saveProduct(product: Product): ResponseEntity<Unit> {
        logger.info("Save product: $product")

        val location: URI =
            productService.save(mapper.map(product))
                .let { p ->
                    ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}").buildAndExpand(p.id).encode()
                        .toUri()
                }

        return ResponseEntity.created(location).build()
    }
}