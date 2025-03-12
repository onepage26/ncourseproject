package com.muhtarkhan.ncourseproject.mainApp.courses.data.ApiService

import com.muhtarkhan.ncourseproject.mainApp.courses.data.model.CatalogResponse
import retrofit2.http.GET

interface CatalogApiService {
    @GET("catalog")
    suspend fun getCatalogData(): CatalogResponse
}