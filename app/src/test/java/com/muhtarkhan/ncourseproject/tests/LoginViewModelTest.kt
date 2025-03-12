package com.muhtarkhan.ncourseproject.tests


import androidx.navigation.NavController
import app.cash.turbine.test
import com.muhtarkhan.ncourseproject.MainDispatcherRule

import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.LoginResponse
import com.muhtarkhan.ncourseproject.appEntryActivity.login.LoginEvent
import com.muhtarkhan.ncourseproject.appEntryActivity.login.LoginState
import com.muhtarkhan.ncourseproject.appEntryActivity.login.LoginViewModel
import com.muhtarkhan.ncourseproject.stubs.AccountProviderStub
import com.muhtarkhan.ncourseproject.stubs.AuthRepositoryStub
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = MainDispatcherRule()

    @Test
    fun `login success`() = runTest {
        // Given
        val authRepository = AuthRepositoryStub(
            Response.success(LoginResponse("token123"))
        )
        val accountProvider = AccountProviderStub()
        val viewModel = LoginViewModel(authRepository, accountProvider)
        val navController = mock<NavController>()

        // When
        viewModel.onEvent(LoginEvent.Login("test", "password"), navController)

        // Then
        viewModel.loginState.test {
            assertEquals(LoginState.Loading, awaitItem())
            val successState = awaitItem() as LoginState.Success
            assertEquals("token123", successState.token)
            assertTrue(accountProvider.isLoggedIn())
        }
    }

    @Test
    fun `login failure - unsuccessful response`() = runTest {
        // Given
        val authRepository = AuthRepositoryStub(
            Response.error(400, mock())
        )
        val accountProvider = AccountProviderStub()
        val viewModel = LoginViewModel(authRepository, accountProvider)
        val navController = mock<NavController>()

        // When
        viewModel.onEvent(LoginEvent.Login("test", "password"), navController)

        // Then
        viewModel.loginState.test {
            assertEquals(LoginState.Loading, awaitItem())
            val errorState = awaitItem() as LoginState.Error
            assertEquals("Login failed", errorState.message)
        }
    }
}