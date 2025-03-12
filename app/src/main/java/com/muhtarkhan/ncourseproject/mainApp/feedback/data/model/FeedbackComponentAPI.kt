package com.muhtarkhan.ncourseproject.mainApp.feedback.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackResponse(
    @SerialName("feedbacks") val feedbacks: List<FeedbackDto>
) {

}

@Serializable
data class FeedbackDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("previousOccupation") val previousOccupation: String,
    @SerialName("currentOccupation") val currentOccupation: String,
    @SerialName("course") val course: String,
    @SerialName("feedback") val feedback: String,
    @SerialName("imageUrl") val imageUrl: String
) 