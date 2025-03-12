package com.muhtarkhan.ncourseproject.apiclient

import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.appEntryActivity.data.network.authInterceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://nfactorialappbackend.onrender.com/"


    fun create(accountProvider: AccountProvider): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(accountProvider))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}