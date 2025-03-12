package com.muhtarkhan.ncourseproject.appEntryActivity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepositoryImpl
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val accountProvider: AccountProvider
) : ViewModel() {


    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState


    fun onEvent(event: LoginEvent, navController: NavController) {
        when (event) {
            is LoginEvent.Login -> {
                println("ViewModel: Login event, navController = $navController")
                println("ViewModel: Login event with login=${event.login}, password=${event.password}")
                loginUser(event.login, event.password, navController)
            }

            LoginEvent.NavigateToRegistration -> {
                navController.navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }


    private fun loginUser(login: String, password: String, navController: NavController) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = withContext(Dispatchers.IO) {
                    authRepository.login(login, password)
                }

                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (token != null) {
                        accountProvider.saveToken(token)
                        _loginState.value = LoginState.Success(token)
                    } else {
                        _loginState.value = LoginState.Error("Ошибка сервера: неверный токен")
                    }
                } else {
                    when (response.code()) {
                        401 -> _loginState.value = LoginState.Error("Неверный логин или пароль")
                        500 -> _loginState.value = LoginState.Error("Ошибка сервера. Попробуйте позже")
                        else -> _loginState.value = LoginState.Error("Ошибка входа: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Ошибка сети: ${e.message ?: "Неизвестная ошибка"}")
            }
        }
    }
}