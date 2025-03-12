package com.muhtarkhan.ncourseproject.mainApp.courses.presentation.consultationImpl

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConsultationApiService {
    @POST("consultation")
    suspend fun sendConsultationRequest(@Body request: ConsultationRequest): Response<Unit>
}
