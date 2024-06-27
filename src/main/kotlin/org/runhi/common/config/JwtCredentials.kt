package org.runhi.common.config

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey

@ConfigurationProperties(prefix = "jwt")
data class JwtCredentials(
    val secretKey: SecretKey,
    val accessTokenExpirationTime: Long,
    val refreshTokenExpirationTime: Long,
) {
    @ConstructorBinding
    constructor(secretKey: String, accessTokenExpirationTime: Long, refreshTokenExpirationTime: Long) : this(
        Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)),
        accessTokenExpirationTime,
        refreshTokenExpirationTime,
    )
}
