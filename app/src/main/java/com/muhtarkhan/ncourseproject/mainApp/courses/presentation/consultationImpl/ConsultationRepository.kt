package com.muhtarkhan.ncourseproject.mainApp.courses.presentation.consultationImpl

class ConsultationRepository(private val apiService: ConsultationApiService) {
    suspend fun sendConsultationRequest(name: String, email: String, phone: String): Boolean {
        return try {
            val response = apiService.sendConsultationRequest(ConsultationRequest(name, email, phone))
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}