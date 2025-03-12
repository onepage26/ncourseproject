package com.muhtarkhan.ncourseproject.mainApp.blogs.presentation

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

class BlogsFragment : Fragment() {
   private val viewModel: BlogsViewModel by viewModel()
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
                BlogsScreen (
                    state = state,
                    onHome = { navController.navigate(R.id.action_blogsFragment_to_homeFragment) },
                    onCourse = { navController.navigate(R.id.action_blogsFragment_to_coursesFragment) },
                    onReport = { navController.navigate(R.id.action_blogsFragment_to_feedback) },
                    onBlog = { navController.navigate(R.id.action_blogsFragment_self) },
                    onEvent = { event -> viewModel.dispatch(event, navController, accountProvider) }
                )
            }
        }
    }
}