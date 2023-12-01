package ru.ifmo.soclosetoheaven.exception

class AuthException(
    message: String? = "Auth error occurred"
) : Exception(message)