package org.runhi.review.domain.repository

import org.runhi.marathon.domain.Marathon
import org.runhi.review.domain.AverageStar
import org.springframework.data.jpa.repository.JpaRepository

interface AverageStarRepository : JpaRepository<AverageStar, Long> {
    fun findByMarathon(marathon: Marathon): AverageStar?
}
