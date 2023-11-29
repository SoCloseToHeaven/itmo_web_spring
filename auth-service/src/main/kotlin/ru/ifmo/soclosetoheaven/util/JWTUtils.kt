package ru.ifmo.soclosetoheaven.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component


@Component
class JWTUtils {

    companion object {
        private const val TOKEN_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }

    fun resolveToken(req: HttpServletRequest) : String? {
        val header = req.getHeader(TOKEN_HEADER)
        if (header == null || !header.startsWith(TOKEN_PREFIX))
            return null
        return header.substring(TOKEN_PREFIX.length)
    }
}