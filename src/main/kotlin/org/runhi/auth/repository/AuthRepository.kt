package org.runhi.auth.repository

import org.runhi.auth.exception.UserNotLoginException
import org.runhi.user.domain.User
import org.springframework.stereotype.Repository
import org.springframework.web.context.annotation.RequestScope

@Repository
@RequestScope
class AuthRepository(
    private var currentUser: User? = null,
) {
    fun getCurrentUser(): User {
        return currentUser ?: throw UserNotLoginException()
    }

    fun getNullableCurrentUser(): User? {
        return currentUser
    }

    fun updateCurrentUser(currentUser: User?) {
        this.currentUser = currentUser
    }
}
