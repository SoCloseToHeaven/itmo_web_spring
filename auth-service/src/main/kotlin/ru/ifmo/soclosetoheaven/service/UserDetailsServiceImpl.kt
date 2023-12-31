package ru.ifmo.soclosetoheaven.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.ifmo.soclosetoheaven.repository.UserRepository


@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {


    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("No user found with name=$username")
        return User.builder()
            .username(user.username)
            .password(user.password)
            .roles("USER") // default role
            .build()
    }

}