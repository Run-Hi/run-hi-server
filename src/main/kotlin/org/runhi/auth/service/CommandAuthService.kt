package org.runhi.auth.service

import org.runhi.auth.domain.Token
import org.runhi.auth.infra.feign.GoogleOauthFeign
import org.runhi.auth.service.implementation.AuthReader
import org.runhi.auth.service.implementation.AuthValidator
import org.runhi.auth.service.implementation.TokenProvider
import org.runhi.auth.util.BearerTokenExtractor
import org.runhi.user.domain.User
import org.runhi.user.service.implementation.UserCreator
import org.runhi.user.service.implementation.UserReader
import org.runhi.user.service.implementation.UserUpdater
import org.runhi.user.service.implementation.UserValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommandAuthService(
    private val tokenProvider: TokenProvider,
    private val authReader: AuthReader,
    private val googleOauthFeign: GoogleOauthFeign,
    private val authValidator: AuthValidator,
    private val userValidator: UserValidator,
    private val userUpdater: UserUpdater,
    private val userCreator: UserCreator,
    private val userReader: UserReader,
) {
    fun login(accessToken: String): Token {
        val user: User = googleOauthFeign.getInfo(accessToken).toUser()
        val updatedUser: User =
            if (userValidator.checkUserExist(user)) {
                userUpdater.updateUser(user)
            } else {
                userCreator.create(user)
            }

        return tokenProvider.createNewTokens(updatedUser)
    }

    fun refresh(bearer: String): Token {
        val refreshToken: String = BearerTokenExtractor.extract(bearer)
        authValidator.shouldRefreshTokenValid(refreshToken)

        val userId: Long = authReader.getIdFromJwt(refreshToken)
        val accessToken: String = tokenProvider.createAccessToken(userReader.readUser(userId))

        return Token(accessToken, refreshToken)
    }
}
