package org.runhi.auth.presentation

import org.runhi.auth.presentation.dto.AccessTokenRequest
import org.runhi.auth.presentation.dto.RefreshTokenRequest
import org.runhi.auth.presentation.dto.TokenResponse
import org.runhi.auth.service.CommandAuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: CommandAuthService,
) {
    @PostMapping("/login")
    fun login(
        @RequestBody accessToken: AccessTokenRequest,
    ): TokenResponse {
        return TokenResponse.from(
            authService.login(accessToken.accessToken),
        )
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody refreshToken: RefreshTokenRequest,
    ): TokenResponse {
        return TokenResponse.from(
            authService.refresh(refreshToken.refreshToken),
        )
    }
}
