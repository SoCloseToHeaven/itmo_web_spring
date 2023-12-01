package ru.ifmo.soclosetoheaven.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.ifmo.soclosetoheaven.dto.ErrorResponse
import ru.ifmo.soclosetoheaven.util.JWTUtils

@Component
class JWTFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtUtils: JWTUtils
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        try {
            val claims = jwtUtils.resolveClaims(req) ?: throw JwtException("Unable to resolve claims")
            if (!jwtUtils.validateClaims(claims)) {
                throw JwtException("JWT token has expired")
            }
            val subject = claims.subject
            val auth = UsernamePasswordAuthenticationToken( // filling with username and unused
                subject,
                null,
                mutableListOf()
            )
            SecurityContextHolder.getContext().authentication = auth
        } catch (ex: Exception) {
            val error = ErrorResponse(
                ex.message ?: "JWT Error"
            )
            res.status = HttpStatus.FORBIDDEN.value()
            res.contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(res.writer, error)
        }
        chain.doFilter(req, res)
    }
}