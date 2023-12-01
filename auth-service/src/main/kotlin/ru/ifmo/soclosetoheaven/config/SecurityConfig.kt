package ru.ifmo.soclosetoheaven.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import ru.ifmo.soclosetoheaven.service.UserDetailsServiceImpl
import kotlin.jvm.Throws


@EnableWebSecurity
@Configuration
class SecurityConfig {

    companion object {
        private val AUTH_WHITELIST = arrayOf(
            "/auth/login",
            "/auth/signup",
            "/health",
        )
    }

    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl


    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain = http
        .csrf { csrf -> csrf.disable() }
        .authorizeHttpRequests { req ->
            req.requestMatchers(*AUTH_WHITELIST).permitAll()
            req.requestMatchers("/**").authenticated()
        }
        .sessionManagement { strategy -> strategy.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        // .addFilterBefore jwt authorization filter UsernamePasswordAuthenticationFilter::class.java
        .build()



    @Bean
    @Throws(Exception::class)
    fun authenticationManager(http: HttpSecurity, passwordEncoder: BCryptPasswordEncoder) : AuthenticationManager {
        val authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
        return authManagerBuilder.build()
    }
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}