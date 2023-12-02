package ru.ifmo.soclosetoheaven.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.ifmo.soclosetoheaven.dto.ErrorResponse
import ru.ifmo.soclosetoheaven.util.JWTUtils

@Component
class JWTFilter(
    private val jwtUtils: JWTUtils,
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        if (jwtUtils.resolveToken(req) == null) {
            chain.doFilter(req, res)
            return
        }

        try {
            val claims = jwtUtils.resolveClaims(req) ?: throw JwtException("Unable to resolve claims")
            if (!jwtUtils.validateClaims(claims)) {
                throw JwtException("JWT token has expired")
            }
            val subject = claims.subject

            req.setAttribute("username", subject)

            val auth = UsernamePasswordAuthenticationToken( // filling with username and unused
                subject,
                null,
                mutableListOf()
            )
            SecurityContextHolder.getContext().authentication = auth
            chain.doFilter(req, res)
        } catch (ex: Exception) {
            val error = ErrorResponse(
                ex.message ?: "JWT Error"
            )
            res.status = HttpStatus.FORBIDDEN.value()
            res.contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(res.writer, error)
        }
    }
}