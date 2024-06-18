package org.runhi.marathon.presentation.dto

import org.runhi.marathon.domain.Marathon
import org.runhi.review.domain.AverageStar
import java.time.LocalDate

data class CardMarathonDto(
    val name: String,
    val location: String,
    val imageUrl: String,
    val isAccepting: Boolean,
    val totalReview: Float,
    val reviewCount: Int,
    val id: Long,
) {
    constructor(marathon: Marathon, averageStar: AverageStar) :
        this(
            marathon.name,
            marathon.location,
            marathon.imageUrl,
            (LocalDate.now() > marathon.startAcceptingDate) &&
                (LocalDate.now() < marathon.endAcceptingDate),
            averageStar.total,
            averageStar.count,
            marathon.id!!,
        )
}
