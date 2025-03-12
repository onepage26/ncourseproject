package com.muhtarkhan.ncourseproject.appEntryActivity.data.network.ApiService


import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.LoginRequest
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.LoginResponse
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.RegisterRequest
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>


}

