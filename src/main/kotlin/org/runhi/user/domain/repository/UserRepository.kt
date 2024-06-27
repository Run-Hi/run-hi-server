package org.runhi.user.domain.repository

import org.runhi.user.domain.User
import org.runhi.user.exception.UserNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean

    fun findUserByEmail(email: String): Optional<User>

    override fun getById(id: Long): User {
        return findById(id)
            .orElseThrow { UserNotFoundException(id) }
    }
}
