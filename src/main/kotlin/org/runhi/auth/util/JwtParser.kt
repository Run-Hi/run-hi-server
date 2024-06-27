package org.runhi.auth.util

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.runhi.auth.exception.TokenExpiredException
import org.runhi.auth.exception.TokenInvalidException
import org.runhi.common.config.JwtCredentials
import org.springframework.stereotype.Component

@Component
class JwtParser(
    private val jwtCredentials: JwtCredentials,
) {
    fun getIdFromJwt(jwt: String?): Long {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(jwtCredentials.secretKey)
                .build()
                .parseClaimsJws(jwt)
                .body[ID]
                .toString().toLong()
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException()
        } catch (e: JwtException) {
            throw TokenInvalidException()
        }
    }

    companion object {
        const val ID: String = "id"
    }
}
