package com.eightbits.eco.retail.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.eightbits.eco.retail.infrastructure.boundaries.outbound.repository"])
class MongoConfiguration {

}