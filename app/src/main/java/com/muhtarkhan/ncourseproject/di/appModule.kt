package com.muhtarkhan.ncourseproject.di


import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepository
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepositoryImpl
import com.muhtarkhan.ncourseproject.appEntryActivity.login.LoginViewModel
import com.muhtarkhan.ncourseproject.appEntryActivity.registration.RegistrationViewModel
import com.muhtarkhan.ncourseproject.mainApp.blogs.data.repository.BlogsRepositoryImpl
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.repository.BlogsRepository
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.usecase.GetBlogsUseCase
import com.muhtarkhan.ncourseproject.mainApp.blogs.presentation.BlogsViewModel
import com.muhtarkhan.ncourseproject.mainApp.courses.data.repository.CatalogRepositoryImp
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.repository.CatalogRepository
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.usecase.GetCatalogUseCase
import com.muhtarkhan.ncourseproject.mainApp.courses.presentation.CatalogViewModel
import com.muhtarkhan.ncourseproject.mainApp.courses.presentation.consultationImpl.ConsultationRepository
import com.muhtarkhan.ncourseproject.mainApp.feedback.presentation.FeedbackViewModel

import com.muhtarkhan.ncourseproject.mainApp.feedback.data.repository.FeedbackRepositoryImpl
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.repository.FeedbackRepository
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.usecase.GetFeedbackUseCase

import com.muhtarkhan.ncourseproject.mainApp.home.data.repository.HomeRepositoryImpl
import com.muhtarkhan.ncourseproject.mainApp.home.domain.repository.HomeRepository
import com.muhtarkhan.ncourseproject.mainApp.home.domain.usecase.GetHomeUseCase
import com.muhtarkhan.ncourseproject.mainApp.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { CatalogViewModel(get(), get()) }
    viewModel { FeedbackViewModel(get()) }
    viewModel { BlogsViewModel(get()) }
    viewModel { RegistrationViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }

    // Репозитории
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<CatalogRepository> { CatalogRepositoryImp(get()) }
    single<FeedbackRepository> { FeedbackRepositoryImpl(get()) }
    single<BlogsRepository> { BlogsRepositoryImpl(get()) }
    single { ConsultationRepository(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    // usecase
    factory { GetHomeUseCase(get()) }
    factory { GetCatalogUseCase(get()) }
    factory { GetBlogsUseCase(get()) }
    factory { GetFeedbackUseCase(get()) }
}
