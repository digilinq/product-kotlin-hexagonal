package com.eightbits.eco.retail.infrastructure.configuration

import com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.listeners.ProductEntityEventListener
import com.mongodb.client.MongoClient
import de.flapdoodle.os.CommonOS
import de.flapdoodle.os.Platform
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@DataMongoTest
@Import(ProductEntityEventListener::class)
class EmbeddedMongoDBTest @Autowired constructor(
    private val applicationContext: ApplicationContext,
    private val env: Environment,
    private val mongoClient:MongoClient
) {

    @Test
    fun `should work`() {
        assertNotNull(mongoClient)
    }

    @Test
    fun `override the OS with Ubuntu`() {
        val os = CommonOS.list()
        println(os)

        val result = Platform.detect(CommonOS.list())
        println(result)
    }

    @Test
    fun `print env variable during the test`() {
        assertEquals("test", env.getProperty("TEST_VAR"))
    }
}
