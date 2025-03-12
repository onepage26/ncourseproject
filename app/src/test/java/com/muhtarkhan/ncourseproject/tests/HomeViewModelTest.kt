package com.muhtarkhan.ncourseproject.tests

import app.cash.turbine.test
import com.muhtarkhan.ncourseproject.MainDispatcherRule
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.Banner
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.Course
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.HomeData
import com.muhtarkhan.ncourseproject.mainApp.home.domain.usecase.GetHomeUseCase
import com.muhtarkhan.ncourseproject.mainApp.home.presentation.HomeViewModel
import com.muhtarkhan.ncourseproject.stubs.HomeRepositoryStub
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `fetchMainData success`() = runTest {
        // Given
        val homeData = HomeData(
            banners = listOf(
                Banner(imageUrl = "https://imgv3.fotor.com/images/blog-cover-image/a-shadow-of-a-boy-carrying-the-camera-with-red-sky-behind.jpg", text = "Banner")
            ),
            courses = listOf(
                Course(
                    id = 1,
                    name = "Android Development",
                    description = "Android development course",
                    price = 590000,
                    duration = "3 month",
                    courseDetails = "course content",
                    tags = listOf("Android", "Mobile", "Kotlin"),
                    imageUrl = "https://imgv3.fotor.com/images/blog-cover-image/a-shadow-of-a-boy-carrying-the-camera-with-red-sky-behind.jpg"
                )
            ),
            tags = listOf("Android", "Kotlin", "Development")
        )
        val homeRepository = HomeRepositoryStub(homeData = homeData)
        val getHomeUseCase = GetHomeUseCase(homeRepository)
        val viewModel = HomeViewModel(getHomeUseCase)

        // When
        // Данные загружаются автоматически в init

        // Then
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(true, initialState.isLoading)

            val successState = awaitItem()
            assertEquals(false, successState.isLoading)
            assertEquals(homeData, successState.mainData)
        }
    }

    @Test
    fun `fetchMainData failure`() = runTest {
        // Given
        val homeRepository = HomeRepositoryStub(exception = RuntimeException("Network error"))
        val getHomeUseCase = GetHomeUseCase(homeRepository)
        val viewModel = HomeViewModel(getHomeUseCase)

        // When
        // Данные загружаются автоматически в init

        // Then
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(true, initialState.isLoading)

            val errorState = awaitItem()
            assertEquals(false, errorState.isLoading)
            assertEquals("Network error", errorState.error)
        }
    }
}