package com.muhtarkhan.ncourseproject.mainApp.feedback.domain.usecase

import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.model.FeedbackData
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.repository.FeedbackRepository
import kotlinx.coroutines.flow.Flow

class GetFeedbackUseCase(private val repository: FeedbackRepository) {
    operator fun invoke(): Flow<FeedbackData> {
        return repository.getFeedback()
    }
}