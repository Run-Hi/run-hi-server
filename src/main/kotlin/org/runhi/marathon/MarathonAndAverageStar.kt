package org.runhi.marathon

import com.querydsl.core.annotations.QueryProjection
import org.runhi.marathon.domain.Marathon
import org.runhi.review.domain.AverageStar

data class MarathonAndAverageStar
    @QueryProjection
    constructor(
        val marathon: Marathon,
        val averageStar: AverageStar,
    )
