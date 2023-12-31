package ru.ifmo.soclosetoheaven.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import ru.ifmo.soclosetoheaven.dto.UserRequest
import ru.ifmo.soclosetoheaven.dto.UserResponse
import ru.ifmo.soclosetoheaven.entity.UserEntity


@Component
class UserMapper(
    private val passwordEncoder: BCryptPasswordEncoder,
) {

    fun mapToResponse(userEntity: UserEntity): UserResponse = UserResponse(
        userEntity.id!!,
        userEntity.username
    )

    fun mapFromRequest(userRequest: UserRequest): UserEntity = UserEntity(
        userRequest.username,
        passwordEncoder.encode(userRequest.password)
    )
}