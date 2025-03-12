package com.muhtarkhan.ncourseproject.mainApp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.R

import com.muhtarkhan.ncourseproject.ui.theme.NcourseProjectTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel() // Теперь Koin сам предоставляет ViewModel
    private val accountProvider: AccountProvider by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val navController = findNavController()
            val state by viewModel.uiState.collectAsState()

            NcourseProjectTheme {
                HomeScreen(
                    state = state,
                    onHome = { navController.navigate(R.id.action_homeFragment_self) },
                    onCourse = { navController.navigate(R.id.action_homeFragment_to_coursesFragment) },
                    onReport = { navController.navigate(R.id.action_homeFragment_to_feedback) },
                    onBlog = { navController.navigate(R.id.action_homeFragment_to_blogsFragment) },
                    onEvent = { event -> viewModel.dispatch(event, navController, accountProvider) }
                )
            }
        }
    }
}