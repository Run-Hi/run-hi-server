package org.runhi.review.domain

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import org.runhi.common.BaseGenerateId
import org.runhi.marathon.domain.Marathon
import org.runhi.user.domain.User
import java.time.LocalDateTime

@Entity
class Review(
    var title: String,
    var level: Int,
    var track: Int,
    var operate: Int,
    var waterStation: Int,
    var souvenir: Int,
    var medal: Int,
    var pros: String,
    var cons: String,
    @ManyToOne
    @JoinColumn(name = "marathon_id")
    val marathon: Marathon,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseGenerateId() {
    @CreationTimestamp
    val createdDateTime: LocalDateTime = LocalDateTime.now()
}
