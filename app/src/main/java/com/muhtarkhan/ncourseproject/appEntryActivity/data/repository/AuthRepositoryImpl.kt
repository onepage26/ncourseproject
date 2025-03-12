package com.muhtarkhan.ncourseproject.appEntryActivity.data.repository

import android.content.Context
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.ManageTokenSharedPref
import com.muhtarkhan.ncourseproject.appEntryActivity.data.network.ApiService.AuthApi
import com.muhtarkhan.ncourseproject.apiclient.RetrofitClient
import retrofit2.Response

class AuthRepositoryImpl(context: Context) : AuthRepository {
    private val accountProvider = ManageTokenSharedPref(context)
    private val authApi = RetrofitClient.create(accountProvider).create(AuthApi::class.java)

    override suspend fun login(login: String, password: String): Response<LoginResponse> {
        return authApi.login(LoginRequest(login, password))
    }

    override suspend fun register(login: String, password: String): Response<RegisterResponse> {
        return authApi.register(RegisterRequest(login, password))
    }
}


data class LoginRequest(val login: String, val password: String)
data class LoginResponse(val token: String)

data class RegisterRequest(val login: String, val password: String)
data class RegisterResponse(val token: String)