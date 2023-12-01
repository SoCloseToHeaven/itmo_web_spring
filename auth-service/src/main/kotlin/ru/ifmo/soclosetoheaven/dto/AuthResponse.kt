package ru.ifmo.soclosetoheaven.dto

data class AuthResponse(
    val token: String,
    val user: UserResponse
)