package org.runhi.auth.presentation.dto

import org.runhi.auth.domain.Token

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun from(token: Token): TokenResponse {
            return TokenResponse(
                token.accessToken,
                token.refreshToken,
            )
        }
    }
}
