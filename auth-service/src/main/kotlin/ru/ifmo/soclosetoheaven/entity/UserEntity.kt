package ru.ifmo.soclosetoheaven.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity(name = "users")
@Table(name = "users")
data class UserEntity(
    @Column(name = "username", unique = true)
    val username: String,
    @Column(name = "password")
    val password: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

}