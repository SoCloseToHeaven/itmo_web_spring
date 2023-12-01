package ru.ifmo.soclosetoheaven.handler

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.ifmo.soclosetoheaven.dto.ErrorResponse
import ru.ifmo.soclosetoheaven.exception.AuthException


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [AuthException::class])
    fun handleException(exception: AuthException) = ResponseEntity
        .badRequest()
        .body(ErrorResponse(exception.message as String)) // exception.message is never null

}