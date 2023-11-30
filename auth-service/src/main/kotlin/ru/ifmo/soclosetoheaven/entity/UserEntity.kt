package ru.ifmo.soclosetoheaven.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity(name = "users")
@Table(name = "users")
data class UserEntity(

    @Column(name = "username", unique = true)
    private val username: String,
    @Column(name = "password")
    private val password: String,

) : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    // override default functions
    override fun getPassword(): String = password
    override fun getUsername(): String = username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()
    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}