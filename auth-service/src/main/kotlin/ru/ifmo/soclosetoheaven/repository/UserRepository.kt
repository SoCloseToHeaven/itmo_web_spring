package ru.ifmo.soclosetoheaven.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.ifmo.soclosetoheaven.entity.UserEntity

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?

    fun findByUsernameAndPassword(username: String, password: String): UserEntity?



}