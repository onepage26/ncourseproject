package com.muhtarkhan.ncourseproject.stubs


import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepository
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.LoginResponse
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.RegisterResponse
import retrofit2.Response

class AuthRepositoryStub(
    private val loginResponse: Response<LoginResponse>? = null,
    private val registerResponse: Response<RegisterResponse>? = null
) : AuthRepository {

    override suspend fun login(username: String, password: String): Response<LoginResponse> {
        return loginResponse ?: throw IllegalStateException("No login response provided")
    }

    override suspend fun register(username: String, password: String): Response<RegisterResponse> {
        return registerResponse ?: throw IllegalStateException("No register response provided")
    }
}