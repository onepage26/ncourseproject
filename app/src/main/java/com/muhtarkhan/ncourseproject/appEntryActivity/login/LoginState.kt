package com.muhtarkhan.ncourseproject.appEntryActivity.login




sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
    val login: String = ""
    val password: String = ""
}