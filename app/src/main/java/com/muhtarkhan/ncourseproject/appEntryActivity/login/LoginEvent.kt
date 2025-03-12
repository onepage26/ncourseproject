package com.muhtarkhan.ncourseproject.appEntryActivity.login




sealed interface LoginEvent {
    data class Login(val login: String, val password: String) : LoginEvent
    object NavigateToRegistration : LoginEvent

}