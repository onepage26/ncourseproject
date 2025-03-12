package com.muhtarkhan.ncourseproject.mainApp.feedback.data.repository

import com.muhtarkhan.ncourseproject.mainApp.feedback.data.api.FeedbackApiService
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.model.Feedback
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.model.FeedbackData
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.repository.FeedbackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FeedbackRepositoryImpl(private val apiService: FeedbackApiService) : FeedbackRepository {
    override fun getFeedback(): Flow<FeedbackData> {
        return flow {
            val response = apiService.getFeedback()
            emit(FeedbackData(response.feedbacks.map { feedback ->
                Feedback(
                    id = feedback.id,
                    name = feedback.name,
                    previousOccupation = feedback.previousOccupation,
                    currentOccupation = feedback.currentOccupation,
                    course = feedback.course,
                    feedback = feedback.feedback,
                    imageUrl = feedback.imageUrl
                )
            }))
        }
    }
}