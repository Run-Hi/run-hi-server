package org.runhi.marathon.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.runhi.common.BaseGenerateId
import java.time.LocalDate

@Entity
class Marathon(
    var name: String,
    var location: String,
    @Column(columnDefinition = "TEXT")
    var imageUrl: String,
    var conductDate: LocalDate,
    var startAcceptingDate: LocalDate,
    var endAcceptingDate: LocalDate,
    @Column(columnDefinition = "TEXT")
    var applyUrl: String,
) : BaseGenerateId()
