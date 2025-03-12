package com.muhtarkhan.ncourseproject.appEntryActivity.data.repository


import retrofit2.Response

interface AuthRepository {
    suspend fun login(username: String, password: String): Response<LoginResponse>
    suspend fun register(username: String, password: String): Response<RegisterResponse>
}