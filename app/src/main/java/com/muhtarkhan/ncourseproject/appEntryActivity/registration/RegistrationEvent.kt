package com.muhtarkhan.ncourseproject.appEntryActivity.registration

interface RegistrationEvent {
    data class Register(val login: String, val password: String) : RegistrationEvent
    object NavigateToLogin : RegistrationEvent

}