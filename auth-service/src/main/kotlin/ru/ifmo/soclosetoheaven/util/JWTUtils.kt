package ru.ifmo.soclosetoheaven.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.ifmo.soclosetoheaven.entity.UserEntity
import java.lang.Exception
import java.util.*


@Component
class JWTUtils {

    companion object {
        private const val TOKEN_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }


    private lateinit var jwtParser: JwtParser

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @Value("\${jwt.expires}")
    private var expires: Long = 604800000 // Week in miliseconds

    @PostConstruct
    fun init() {
        jwtParser = Jwts.parser().setSigningKey(secretKey)
    }

    fun resolveToken(req: HttpServletRequest) : String? {
        val header = req.getHeader(TOKEN_HEADER)
        if (header == null || !header.startsWith(TOKEN_PREFIX))
            return null
        return header.substring(TOKEN_PREFIX.length)
    }

    fun resolveClaims(req: HttpServletRequest) : Claims? {
        val token = resolveToken(req) ?: return null
        return try {
            jwtParser.parseClaimsJws(token).body
        } catch (ex: ExpiredJwtException) {
            req.setAttribute("expired", ex.message)
            null
        } catch (ex: Exception) {
            req.setAttribute("invalid", ex.message)
            null
        }
    }

    fun newToken(userEntity: UserEntity) : String {
        val claims = Jwts.claims()
        claims.subject = userEntity.username
        claims["userId"] = userEntity.id
        val expirationDate = Date(System.currentTimeMillis() + expires)
        return Jwts
            .builder()
            .setClaims(claims)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun validateClaims(claims: Claims) : Boolean = claims.expiration?.after(Date()) ?: false
}