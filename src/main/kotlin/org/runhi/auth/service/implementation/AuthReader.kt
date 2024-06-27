package org.runhi.auth.service.implementation

import org.runhi.auth.repository.AuthRepository
import org.runhi.auth.util.JwtParser
import org.runhi.user.domain.User
import org.springframework.stereotype.Service

@Service
class AuthReader(
    private val authRepository: AuthRepository,
    private val jwtParser: JwtParser,
) {
    fun getIdFromJwt(token: String?): Long {
        return jwtParser.getIdFromJwt(token)
    }

    val currentUser: User
        get() = authRepository.getCurrentUser()

    val nullableCurrentUser: User?
        get() = authRepository.getNullableCurrentUser()
}
