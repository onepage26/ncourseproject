package com.muhtarkhan.ncourseproject.mainApp.feedback.domain.model

data class Feedback(
    val id: Int,
    val name: String,
    val previousOccupation: String,
    val currentOccupation: String,
    val course: String,
    val feedback: String,
    val imageUrl: String
)

data class FeedbackData(
    val feedbacks: List<Feedback>
)