package org.runhi.user.service.implementation

import org.runhi.user.domain.User
import org.runhi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCreator(
    private val userRepository: UserRepository,
) {
    fun create(user: User): User {
        return userRepository.save(user)
    }
}
