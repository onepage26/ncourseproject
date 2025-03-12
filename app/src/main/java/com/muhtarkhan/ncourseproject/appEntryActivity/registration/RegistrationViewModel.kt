package com.muhtarkhan.ncourseproject.appEntryActivity.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val authRepository: AuthRepository,
    private val accountProvider: AccountProvider
) : ViewModel() {

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun onEvent(event: RegistrationEvent, navController: NavController) {
        println("RegistrationViewModel: onEvent called with event - $event")
        when (event) {

            is RegistrationEvent.Register -> {
                println("RegistrationViewModel: Register event - login: ${event.login}, " + "" + "password: ${event.password}")
                registerUser(event.login, event.password, navController)
            }
            RegistrationEvent.NavigateToLogin -> {
                println("RegistrationViewModel: NavigateToLogin event")
                navController.navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        }
    }

    private fun registerUser(login: String, password: String, navController: NavController) {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            try {
                val response = authRepository.register(login, password)
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (token != null) {
                        accountProvider.saveToken(token)
                        _registrationState.value = RegistrationState.Success(token)
                        navController.navigate(R.id.action_registrationFragment_to_homeFragment)
                    } else {
                        _registrationState.value = RegistrationState.Error("Токен отсуствует")
                    }
                } else {
                    _registrationState.value = RegistrationState.Error("Должно быть минимум 5 символов")
                }
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}