package ru.ifmo.soclosetoheaven.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class HealthController {


    @GetMapping("/health")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun checkHealth() {}
}