package org.runhi.auth.domain

data class Token(
    val accessToken: String,
    val refreshToken: String,
)
