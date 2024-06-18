package org.runhi.review.controller.dto

class ReviewRequestDto(
    val id: Long,
    val level: Int,
    val track: Int,
    val operate: Int,
    val water: Int,
    val souvenir: Int,
    val medal: Int,
    val title: String,
    val pros: String,
    val cons: String,
)
