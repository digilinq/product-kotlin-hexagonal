package com.eightbits.eco.retail.infrastructure.configuration

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ActuatorConfiguration {

    @Bean
    fun httpExchangeRepository(): HttpExchangeRepository {
        return InMemoryHttpExchangeRepository()
    }
}