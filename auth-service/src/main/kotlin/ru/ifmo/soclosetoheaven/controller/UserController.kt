package ru.ifmo.soclosetoheaven.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.soclosetoheaven.dto.UserResponse
import ru.ifmo.soclosetoheaven.service.UserService


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/info")
    fun getInfo(@RequestAttribute("username") username: String): UserResponse = userService.getUserByName(username)
}