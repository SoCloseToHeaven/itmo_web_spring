package ru.ifmo.soclosetoheaven.util

import org.springframework.stereotype.Component
import ru.ifmo.soclosetoheaven.dto.UserRequest
import ru.ifmo.soclosetoheaven.dto.UserResponse
import ru.ifmo.soclosetoheaven.entity.UserEntity


@Component
class UserMapper {
    fun mapToResponse(userEntity: UserEntity) = UserResponse(userEntity.id!!, userEntity.username)

    fun mapFromRequest(userRequest: UserRequest) = UserEntity(userRequest.username, userRequest.password)
}