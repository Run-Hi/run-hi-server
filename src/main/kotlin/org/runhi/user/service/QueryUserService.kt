package org.runhi.user.service

import org.runhi.user.domain.User
import org.runhi.user.service.implementation.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryUserService(
    private val userReader: UserReader,
) {
    fun readById(id: Long): User {
        return userReader.readUser(id)
    }
}
