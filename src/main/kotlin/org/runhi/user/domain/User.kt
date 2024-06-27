package org.runhi.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.runhi.common.BaseGenerateId
import org.runhi.user.domain.role.Role

@Entity
class User(
    @Enumerated(EnumType.STRING)
    val role: Role,
    var name: String,
    var email: String,
) : BaseGenerateId() {
    fun update(user: User) {
        this.name = user.name
        this.email = user.email
    }
}
