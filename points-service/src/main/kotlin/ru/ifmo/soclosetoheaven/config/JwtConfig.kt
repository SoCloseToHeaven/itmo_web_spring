package ru.ifmo.soclosetoheaven.config

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import ru.ifmo.soclosetoheaven.dto.JwtDTO

@Configuration
class JwtConfig {

    private lateinit var jwtParser: JwtParser

    companion object {
        private const val HEADER = "Authorization"
        private const val STARTER = "Bearer "
    }


    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    @PostConstruct
    fun init() {
        jwtParser = Jwts.parser().setSigningKey(secretKey)
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun getJwtData(req: HttpServletRequest): JwtDTO { // no checking, because API Gateway validates it
        val header = req.getHeader(HEADER)
        if (header == null || !header.startsWith(STARTER))
            throw JwtException("Invalid header")

        val token = header.substring(STARTER.length)
        val claims = jwtParser.parseClaimsJws(token).body

        val subject = claims.subject ?: throw JwtException("JWT Subject is null")
        val userId = claims.get("userId", Integer::class.java).toLong()

        return JwtDTO(
            userId,
            subject
        )
    }
}