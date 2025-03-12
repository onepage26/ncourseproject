package com.muhtarkhan.ncourseproject.appEntryActivity.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.appEntryActivity.data.repository.AuthRepository
import com.muhtarkhan.ncourseproject.appEntryActivity.extensions.LoginViewModelFactory
import com.muhtarkhan.ncourseproject.ui.theme.NcourseProjectTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {

        setContent {
            val navController = findNavController()
            val loginState by viewModel.loginState.collectAsState()

            NcourseProjectTheme {
                LoginScreen(
                    loginState = loginState,
                    onEvent = { event, navController ->
                        viewModel.onEvent(event, navController)
                    },
                    navController = navController
                )
            }
        }
    }
}