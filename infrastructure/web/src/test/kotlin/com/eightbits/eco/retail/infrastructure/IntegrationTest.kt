package com.eightbits.eco.retail.infrastructure

import org.junit.jupiter.api.Tag
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@Tag("IntegrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["server.port=0"])
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Retention(AnnotationRetention.RUNTIME)
annotation class IntegrationTest
