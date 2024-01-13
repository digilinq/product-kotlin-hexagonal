package com.eightbits.eco.retail.common.utils

import com.eightbits.eco.retail.infrastructure.configuration.JacksonConfiguration
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import java.nio.charset.Charset

val objectMapper = JacksonConfiguration.objectMapper
fun readAsString(path: String): String {
    val resource = ClassPathResource(path)
    return resource.getContentAsString(Charset.defaultCharset())
}

private fun <T> readAsType(path: String, type: Class<T>): T {
    val resource = ClassPathResource(path)
    return objectMapper.readValue(resource.inputStream, type)
}

private inline fun <reified T> readAsType(path: String): T {
    val resource = ClassPathResource(path)
    return objectMapper.readValue(resource.inputStream)
}

interface ResourceApiModel<T> {
    val type: Class<T>
    val path: String
    val instance: T
        get() = readApiModel()
    val string: String
        get() = readAsString()

    fun readApiModel(): T {
        return readAsType(path, type)
    }

    fun readAsString(): String {
        return readAsString(path)
    }
}