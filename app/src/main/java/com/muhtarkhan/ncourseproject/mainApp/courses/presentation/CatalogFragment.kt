package com.muhtarkhan.ncourseproject.mainApp.courses.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.ui.theme.NcourseProjectTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogFragment : Fragment() {

    private val viewModel: CatalogViewModel by viewModel()
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
                CatalogScreen(
                    state = state,
                    onHome = { navController.navigate(R.id.action_coursesFragment_to_homeFragment) },
                    onCourse = { navController.navigate(R.id.action_coursesFragment_self) },
                    onReport = { navController.navigate(R.id.action_coursesFragment_to_feedback) },
                    onBlog = { navController.navigate(R.id.action_coursesFragment_to_blogsFragment) },
                    onLogOut = { event -> viewModel.dispatch(event, navController, accountProvider) },
                    onEvent = { event -> viewModel.dispatch(event, navController, accountProvider) }
                )
            }
        }
    }
}

