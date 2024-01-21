package com.eightbits.eco.retail.infrastructure.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.eightbits.eco.retail.infrastructure.boundaries.outbound.mongodb.repository"])
class MongoConfiguration {

}