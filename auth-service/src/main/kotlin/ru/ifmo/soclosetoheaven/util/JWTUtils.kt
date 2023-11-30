package ru.ifmo.soclosetoheaven.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class JWTUtils {

    companion object {
        private const val TOKEN_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }


    @Value("\${jwt.secret}")
    lateinit var secret: String

    @Value("\${jwt.expires}")
    private var expires: Long = 604800000 // Week in miliseconds


    fun resolveToken(req: HttpServletRequest) : String? {
        val header = req.getHeader(TOKEN_HEADER)
        if (header == null || !header.startsWith(TOKEN_PREFIX))
            return null
        return header.substring(TOKEN_PREFIX.length)
    }
}