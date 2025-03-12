package com.muhtarkhan.ncourseproject.appEntryActivity.registration

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    data class Success(val token: String) : RegistrationState()
    data class Error(val message: String) : RegistrationState()

}