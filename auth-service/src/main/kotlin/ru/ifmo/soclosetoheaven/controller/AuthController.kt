package ru.ifmo.soclosetoheaven.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.soclosetoheaven.dto.AuthResponse
import ru.ifmo.soclosetoheaven.dto.UserRequest
import ru.ifmo.soclosetoheaven.service.UserService


@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping(value = ["/login"])
    fun login(@RequestBody userRequest: UserRequest): AuthResponse = userService.login(userRequest)

    @PostMapping(value = ["/signup"])
    fun signup(@RequestBody userRequest: UserRequest): AuthResponse = userService.signup(userRequest)
}