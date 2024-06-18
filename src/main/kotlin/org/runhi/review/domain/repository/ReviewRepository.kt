package org.runhi.review.domain.repository

import org.runhi.review.domain.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ReviewRepository : JpaRepository<Review, Long> {
    @Query("select r from Review r where r.marathon.id = :id order by r.createdDateTime desc")
    fun findByMarathonId(id: Long): List<Review>
}
