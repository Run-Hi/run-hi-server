package org.runhi.review.controller

import org.runhi.auth.annotation.LoginRequired
import org.runhi.auth.repository.AuthRepository
import org.runhi.review.controller.dto.ReviewRequestDto
import org.runhi.review.controller.dto.ReviewResponseDto
import org.runhi.review.domain.Review
import org.runhi.review.service.ReviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val reviewService: ReviewService,
    private val authRepository: AuthRepository,
) {
    @GetMapping("/{id}")
    fun getReviewByMarathonId(
        @PathVariable id: Long,
    ): List<ReviewResponseDto> {
        return reviewService.getReviewByMarathonId(id)
            .map { review: Review -> ReviewResponseDto(review) }
    }

    @PostMapping
    @LoginRequired
    fun createReview(
        @RequestBody request: ReviewRequestDto,
    ) {
        reviewService.create(request, authRepository.getCurrentUser())
    }
}
