package com.muhtarkhan.ncourseproject.appEntryActivity.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepository
import com.muhtarkhan.ncourseproject.appEntryActivity.registration.RegistrationViewModel

class RegistrationViewModelFactory(
    private val authRepository: AuthRepository,
    private val accountProvider: AccountProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(authRepository, accountProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
