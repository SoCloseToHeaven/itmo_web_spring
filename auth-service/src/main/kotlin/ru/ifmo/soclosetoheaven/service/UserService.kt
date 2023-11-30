package ru.ifmo.soclosetoheaven.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import ru.ifmo.soclosetoheaven.dto.UserRequest
import ru.ifmo.soclosetoheaven.dto.UserResponse
import ru.ifmo.soclosetoheaven.repository.UserRepository
import ru.ifmo.soclosetoheaven.util.UserMapper


@Service
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var userRepository: UserRepository


    fun getUserByName(name: String) = userMapper.mapToResponse(
        userRepository.findByUsername(name) ?: throw UsernameNotFoundException("User with name=$name was not found")
    )

    fun getUserById(id: Long) = userMapper.mapToResponse(
        userRepository.findById(id).orElseThrow { UsernameNotFoundException("User with id=$id was not found") }
    )

    fun createNewUser(userRequest: UserRequest) : UserResponse {
        val userEntity = userMapper.mapFromRequest(userRequest)
        userRepository.save(userEntity)
        return userMapper.mapToResponse(userEntity)
    }
}