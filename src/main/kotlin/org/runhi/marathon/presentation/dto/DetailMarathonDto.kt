package org.runhi.marathon.presentation.dto

import org.runhi.marathon.domain.Marathon
import org.runhi.review.domain.AverageStar
import java.time.LocalDate

data class DetailMarathonDto(
    val name: String,
    val location: String,
    val imageUrl: String,
    val isAccepting: Boolean,
    val conductDate: LocalDate,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val totalReview: Float,
    val reviewCount: Int,
    val id: Long,
    val total: Float,
    val level: Float,
    val track: Float,
    val operate: Float,
    val waterStation: Float,
    val souvenir: Float,
    val medal: Float,
    val fee: Int,
    val applyUrl: String,
) {
    constructor(
        marathon: Marathon,
        averageStar: AverageStar,
    ) :
        this(
            marathon.name,
            marathon.location,
            marathon.imageUrl,
            (LocalDate.now() > marathon.startAcceptingDate) &&
                (LocalDate.now() < marathon.endAcceptingDate),
            marathon.conductDate,
            marathon.startAcceptingDate,
            marathon.endAcceptingDate,
            averageStar.total,
            averageStar.count,
            marathon.id!!,
            averageStar.total,
            averageStar.level,
            averageStar.track,
            averageStar.operate,
            averageStar.waterStation,
            averageStar.souvenir,
            averageStar.medal,
            marathon.fee,
            marathon.applyUrl,
        )
}
