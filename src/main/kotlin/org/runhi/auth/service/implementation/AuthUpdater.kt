package org.runhi.auth.service.implementation

import org.runhi.auth.repository.AuthRepository
import org.runhi.user.domain.User
import org.springframework.stereotype.Service

@Service
class AuthUpdater(
    private val authRepository: AuthRepository,
) {
    fun updateCurrentUser(user: User) {
        authRepository.updateCurrentUser(user)
    }
}
