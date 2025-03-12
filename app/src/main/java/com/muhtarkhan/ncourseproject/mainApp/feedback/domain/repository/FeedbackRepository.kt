package com.muhtarkhan.ncourseproject.mainApp.feedback.domain.repository

import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.model.FeedbackData
import kotlinx.coroutines.flow.Flow

interface FeedbackRepository {
    fun getFeedback(): Flow<FeedbackData>
}