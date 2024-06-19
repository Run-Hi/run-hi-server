package org.runhi.review.service

import org.runhi.marathon.domain.repository.MarathonRepository
import org.runhi.review.controller.dto.ReviewRequestDto
import org.runhi.review.domain.Review
import org.runhi.review.domain.repository.AverageStarRepository
import org.runhi.review.domain.repository.ReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val marathonRepository: MarathonRepository,
    private val averageStarRepository: AverageStarRepository,
) {
    fun getReviewByMarathonId(id: Long): List<Review> {
        return reviewRepository.findByMarathonId(id)
    }

    fun create(request: ReviewRequestDto) {
        val marathon = marathonRepository.findById(request.id).orElseThrow { RuntimeException("marathonNotFound") }

        val averageStar = averageStarRepository.findByMarathon(marathon) ?: throw RuntimeException()

        val savingReview =
            Review(
                title = request.title,
                level = request.level,
                track = request.track,
                operate = request.operate,
                waterStation = request.water,
                souvenir = request.souvenir,
                medal = request.medal,
                pros = request.pros,
                cons = request.cons,
                marathon = marathon,
            )

        averageStar.updateReview(savingReview)

        reviewRepository.save(savingReview)
    }
}
