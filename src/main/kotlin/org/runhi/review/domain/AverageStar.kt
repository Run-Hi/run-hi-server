package org.runhi.review.domain

import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import org.runhi.common.BaseGenerateId
import org.runhi.marathon.domain.Marathon

@Entity
class AverageStar(
    var total: Float,
    var level: Float,
    var track: Float,
    var operate: Float,
    var waterStation: Float,
    var souvenir: Float,
    var medal: Float,
    var count: Int,
    @OneToOne
    val marathon: Marathon,
) : BaseGenerateId()
