package org.runhi.auth.service.implementation

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.runhi.auth.exception.TokenExpiredException
import org.runhi.auth.exception.TokenInvalidException
import org.runhi.common.config.JwtCredentials
import org.springframework.stereotype.Service

@Service
class AuthValidator(
    private val jwtCredentials: JwtCredentials,
) {
    fun shouldRefreshTokenValid(refreshToken: String?) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtCredentials.secretKey)
                .build()
                .parseClaimsJws(refreshToken)
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException()
        } catch (e: JwtException) {
            throw TokenInvalidException()
        }
    }
}
