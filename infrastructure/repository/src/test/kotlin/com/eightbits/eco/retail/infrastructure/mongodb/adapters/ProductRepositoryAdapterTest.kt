package com.eightbits.eco.retail.infrastructure.mongodb.adapters

import com.eightbits.eco.retail.common.mockdata.MockEntityModel
import com.eightbits.eco.retail.infrastructure.mongodb.listeners.ProductEntityEventListener
import com.eightbits.eco.retail.infrastructure.mongodb.repository.ProductRepository
import com.mongodb.DBObject
import com.mongodb.client.MongoClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.Import
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataMongoTest
@Import(ProductEntityEventListener::class)
class ProductRepositoryAdapterTest @Autowired constructor(
        private val mongoClient: MongoClient,
        private val mongoTemplate: MongoTemplate,
        private val productRepository: ProductRepository
) {
    @Test
    fun `should work`() {
        assertNotNull(mongoClient)
        assertNotNull(mongoTemplate)
    }

    @Test
    fun `print database names`() {
        assertThat(mongoClient.listDatabases()).extracting("name").contains("test-db")
    }

    @Test
    fun `database should have products as collection`() {
        assertThat(mongoClient.getDatabase("test-db").listCollections()).extracting("name").containsOnly("products")
    }

    @Test
    fun `save with repository and retrieve with mongo template`() {
        productRepository.save(MockEntityModel.ProductEntityExamples.PRODUCT_WITH_EMPTY_ID.instance)
        val products = mongoTemplate.findAll(DBObject::class.java, "products")
        println(products)
    }

    @Test
    fun `should generate objectId and productId while saving to database`() {
        val product = productRepository.save(MockEntityModel.ProductEntityExamples.PRODUCT_WITH_EMPTY_ID.instance)
        assertNotNull(product.id)
        assertNotNull(product.productId)
    }
}