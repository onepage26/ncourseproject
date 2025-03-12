package com.muhtarkhan.ncourseproject.appEntryActivity.extensions

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepository
import com.muhtarkhan.ncourseproject.appEntryActivity.login.LoginViewModel

class LoginViewModelFactory(
    private val authRepository: AuthRepository,
    private val accountProvider: AccountProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(authRepository, accountProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}