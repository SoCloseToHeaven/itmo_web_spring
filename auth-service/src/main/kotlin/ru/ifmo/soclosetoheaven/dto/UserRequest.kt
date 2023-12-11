package ru.ifmo.soclosetoheaven.dto

import jakarta.validation.constraints.Size

data class UserRequest(
    @field: Size(min = 4, max = 20)
    val username: String,
    @field: Size(min = 7, max = 20)
    val password: String,
)