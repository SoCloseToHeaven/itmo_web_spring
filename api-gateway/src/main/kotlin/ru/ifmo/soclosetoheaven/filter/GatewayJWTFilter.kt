package ru.ifmo.soclosetoheaven.filter

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

@Component
class GatewayJWTFilter : GatewayFilter {

    companion object {
        private const val HEADER = "Authorization"
        private const val STARTER = "Bearer "
    }

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    lateinit var jwtParser: JwtParser


    // hardcode.........
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val req = exchange.request
        val res = exchange.response
        try {
            val header = req.headers.getFirst(HEADER) ?: throw JwtException("Unable to resolve token")
            if (!header.startsWith(STARTER))
                throw JwtException("No bearer in header")
            val token = header.substring(STARTER.length)
            val claims = jwtParser.parseClaimsJws(token).body
            val exp = claims.expiration ?: throw JwtException("Expiration is null")
            if (exp.before(Date()))
                throw JwtException("Token is expired")
            return chain.filter(exchange)
        } catch (ex: Exception) {
            res.statusCode = HttpStatus.FORBIDDEN
            return res.setComplete()
        }
    }


    @PostConstruct
    fun init() {
        jwtParser = Jwts.parser().setSigningKey(secretKey)
    }

}