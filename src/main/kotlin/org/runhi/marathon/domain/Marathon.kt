package org.runhi.marathon.domain

import jakarta.persistence.Entity
import org.runhi.common.BaseGenerateId
import java.time.LocalDate

@Entity
class Marathon(
    var name: String,
    var location: String,
    var imageUrl: String,
    var conductDate: LocalDate,
    var startAcceptingDate: LocalDate,
    var endAcceptingDate: LocalDate,
    var applyUrl: String,
    var fee: Int,
) : BaseGenerateId()
