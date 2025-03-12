package com.muhtarkhan.ncourseproject.appEntryActivity.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import org.koin.androidx.viewmodel.ext.android.viewModel
import com.muhtarkhan.ncourseproject.ui.theme.NcourseProjectTheme


class RegistrationFragment : Fragment() {
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val navController = findNavController()
            val registrationState by viewModel.registrationState.collectAsState()

            NcourseProjectTheme {
                RegistrationScreen(
                    registrationState = registrationState,
                    onEvent = { event, navController ->
                        viewModel.onEvent(event, navController)
                    },
                    navController = navController
                )
            }
        }
    }
}