package com.muhtarkhan.ncourseproject.appEntryActivity.data.network.authInterceptor

import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody


class AuthInterceptor(private val accountProvider: AccountProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${accountProvider.getToken()}")
            .build()
        val token = accountProvider.getToken()
        println("Access Token: $token")
        println("Request URL: ${request.url}")

        val response = chain.proceed(request)


        val responseBody = response.body
        val responseBodyString = responseBody?.string() ?: ""

        println("Response Code: ${response.code}")
        println("Response Body: $responseBodyString")


        return response.newBuilder()
            .body(ResponseBody.create(responseBody?.contentType(), responseBodyString))
            .build()
    }
}