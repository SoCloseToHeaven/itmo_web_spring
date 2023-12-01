package ru.ifmo.soclosetoheaven.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.ifmo.soclosetoheaven.dto.AuthResponse
import ru.ifmo.soclosetoheaven.dto.UserRequest
import ru.ifmo.soclosetoheaven.entity.UserEntity
import ru.ifmo.soclosetoheaven.exception.AuthException
import ru.ifmo.soclosetoheaven.repository.UserRepository
import ru.ifmo.soclosetoheaven.util.JWTUtils
import ru.ifmo.soclosetoheaven.util.UserMapper
import kotlin.jvm.Throws


@Service
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var jwtUtils: JWTUtils

    @Autowired
    private lateinit var passwordEncoder: BCryptPasswordEncoder


    fun getUserByName(name: String) = userMapper.mapToResponse(
        userRepository.findByUsername(name) ?: throw UsernameNotFoundException("User with name=$name was not found")
    )

    fun getUserById(id: Long) = userMapper.mapToResponse(
        userRepository.findById(id).orElseThrow { UsernameNotFoundException("User with id=$id was not found") }
    )



    private fun createNewUser(userRequest: UserRequest) : UserEntity {
        val userEntity = userMapper.mapFromRequest(userRequest)
        userRepository.save(userEntity)
        return userEntity
    }


    @Throws(AuthException::class)
    fun login(userRequest: UserRequest) : AuthResponse {
        val userEntity = userRepository
            .findByUsernameAndPassword(
                userRequest.username,
                passwordEncoder.encode(userRequest.password)
            ) ?: throw AuthException("Invalid username or password")
        val token = jwtUtils.newToken(userEntity)
        return AuthResponse(
            token,
            userMapper.mapToResponse(userEntity)
        )
    }


    @Throws(AuthException::class)
    @Transactional
    fun signup(userRequest: UserRequest) : AuthResponse {
        val optionalUserEntity = userRepository.findByUsername(userRequest.username)
        if (optionalUserEntity != null)
            throw AuthException("User with name=${userRequest.username} already exists")
        val userEntity = createNewUser(userRequest)
        val token = jwtUtils.newToken(userEntity)
        return AuthResponse(
            token,
            userMapper.mapToResponse(userEntity)
        )
    }
}