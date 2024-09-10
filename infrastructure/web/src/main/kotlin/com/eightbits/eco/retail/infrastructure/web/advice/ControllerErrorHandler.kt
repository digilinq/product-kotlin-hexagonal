package com.eightbits.eco.retail.infrastructure.web.advice

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ControllerErrorHandler : ResponseEntityExceptionHandler() {
    protected val log = LoggerFactory.getLogger(ControllerErrorHandler::class.java)
    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        log.warn(ex.message, ex)
        return super.handleExceptionInternal(ex, body, headers, statusCode, request)
    }
}