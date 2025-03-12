package com.muhtarkhan.ncourseproject.mainApp.feedback.presentation

import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.model.FeedbackData

data class FeedbackUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val feedbackData: FeedbackData? = null
)