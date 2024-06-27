package org.runhi.user.service.implementation

import org.runhi.user.domain.User
import org.runhi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserValidator(
    private val userRepository: UserRepository,
) {
    fun checkUserExist(user: User): Boolean {
        return userRepository.existsByEmail(user.email)
    }
}
