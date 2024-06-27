package org.runhi.user.service.implementation

import org.runhi.user.domain.User
import org.runhi.user.domain.repository.UserRepository
import org.runhi.user.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class UserUpdater(
    private val userRepository: UserRepository,
) {
    fun updateUser(user: User): User {
        val updatableUser: User =
            userRepository.findUserByEmail(user.email).orElseThrow { UserNotFoundException(user.email) }

        updatableUser.update(user)

        return updatableUser
    }
}
