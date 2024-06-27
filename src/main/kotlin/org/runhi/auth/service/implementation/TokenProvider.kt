package org.runhi.auth.service.implementation

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.runhi.auth.domain.Token
import org.runhi.common.config.JwtCredentials
import org.runhi.user.domain.User
import org.springframework.stereotype.Service
import java.util.Date

@Service
class TokenProvider(
    private val jwtCredentials: JwtCredentials,
) {
    fun createNewTokens(user: User): Token {
        return Token(
            createAccessToken(user),
            createRefreshToken(user),
        )
    }

    fun createAccessToken(user: User): String {
        return createToken(user, jwtCredentials.accessTokenExpirationTime)
    }

    private fun createRefreshToken(user: User): String {
        return createToken(user, jwtCredentials.refreshTokenExpirationTime)
    }

    private fun createToken(
        user: User,
        expireLength: Long,
    ): String {
        val now = Date()
        val expiration = Date(System.currentTimeMillis() + expireLength)
        return Jwts.builder()
            .claim("id", user.id)
            .setExpiration(expiration)
            .signWith(jwtCredentials.secretKey, SignatureAlgorithm.HS256)
            .compact()
    }
}
