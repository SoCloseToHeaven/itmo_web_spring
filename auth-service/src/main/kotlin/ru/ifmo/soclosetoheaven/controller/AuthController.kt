package ru.ifmo.soclosetoheaven.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.soclosetoheaven.dto.AuthResponse
import ru.ifmo.soclosetoheaven.dto.UserRequest
import ru.ifmo.soclosetoheaven.service.UserService


@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
) {

    @PostMapping(value = ["/login"])
    fun login(@Valid @RequestBody userRequest: UserRequest): AuthResponse = userService.login(userRequest)

    @PostMapping(value = ["/signup"])
    fun signup(@Valid @RequestBody userRequest: UserRequest): AuthResponse = userService.signup(userRequest)
}