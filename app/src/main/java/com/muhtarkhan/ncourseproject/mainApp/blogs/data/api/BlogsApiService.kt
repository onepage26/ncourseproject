package com.muhtarkhan.ncourseproject.mainApp.blogs.data.api

import com.muhtarkhan.ncourseproject.mainApp.blogs.data.model.BlogsResponse
import retrofit2.http.GET

interface BlogsApiService {
    @GET("news")
    suspend fun getBlogs(): BlogsResponse
}