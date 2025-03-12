package com.muhtarkhan.ncourseproject.di

import com.muhtarkhan.ncourseproject.apiclient.RetrofitClient
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.ManageTokenSharedPref
import com.muhtarkhan.ncourseproject.mainApp.blogs.data.api.BlogsApiService
import com.muhtarkhan.ncourseproject.mainApp.courses.data.ApiService.CatalogApiService
import com.muhtarkhan.ncourseproject.mainApp.courses.presentation.consultationImpl.ConsultationApiService
import com.muhtarkhan.ncourseproject.mainApp.feedback.data.api.FeedbackApiService
import com.muhtarkhan.ncourseproject.mainApp.home.data.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    // AccountProvider (хранение токена)
    single<AccountProvider> { ManageTokenSharedPref(get()) }

    // Retrofit
    single { RetrofitClient.create(get()) }

    // API сервисы
    single { get<Retrofit>().create(ApiService::class.java) }
    single { get<Retrofit>().create(CatalogApiService::class.java) }
    single { get<Retrofit>().create(FeedbackApiService::class.java) }
    single { get<Retrofit>().create(BlogsApiService::class.java) }
    single { get<Retrofit>().create(ConsultationApiService::class.java) }


}