package com.muhtarkhan.ncourseproject.mainApp.home.data.api

import com.muhtarkhan.ncourseproject.mainApp.home.data.model.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("main")
    suspend fun getMainData(@Query("tag") tag: String? = null): MainResponse
}
