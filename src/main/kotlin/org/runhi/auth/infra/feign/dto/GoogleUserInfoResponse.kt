package org.runhi.auth.infra.feign.dto

import org.runhi.user.domain.User
import org.runhi.user.domain.role.Role

data class GoogleUserInfoResponse(
    val email: String,
    val name: String,
) {
    fun toUser(): User {
        return User(Role.USER, name, email)
    }
}
