package org.runhi.review.controller.dto

import org.runhi.review.domain.Review
import java.time.LocalDateTime

class ReviewResponseDto(
    val title: String,
    val pros: String,
    val cons: String,
    val createdDateTime: LocalDateTime,
    val total: Float,
) {
    constructor(review: Review) : this(
        review.title,
        review.pros,
        review.cons,
        review.createdDateTime,
        (review.track + review.operate + review.waterStation + review.souvenir + review.medal).toFloat() / 5,
    )
}
