package org.runhi.user.service.implementation

import org.runhi.user.domain.User
import org.runhi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserReader(
    private val userRepository: UserRepository,
) {
    fun readUser(id: Long): User {
        return userRepository.getById(id)
    }
}
