package com.muhtarkhan.ncourseproject.mainApp.feedback.data.api

import com.muhtarkhan.ncourseproject.mainApp.feedback.data.model.FeedbackResponse
import retrofit2.http.GET

interface FeedbackApiService {

    @GET("feedback")
    suspend fun getFeedback(): FeedbackResponse
}