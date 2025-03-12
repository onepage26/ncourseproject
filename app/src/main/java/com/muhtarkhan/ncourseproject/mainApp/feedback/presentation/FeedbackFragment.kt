package com.muhtarkhan.ncourseproject.mainApp.feedback.presentation

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

class FeedbackFragment : Fragment() {
    private val viewModel: FeedbackViewModel by viewModel()
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
                FeedbackScreen (
                    state = state,
                    onHome = { navController.navigate(R.id.action_feedback_to_homeFragment) },
                    onCourse = { navController.navigate(R.id.action_feedback_to_coursesFragment) },
                    onReport = { navController.navigate(R.id.action_feedback_self) },
                    onBlog = { navController.navigate(R.id.action_feedback_to_blogsFragment) },
                    onLogOut = { event -> viewModel.dispatch(event, navController, accountProvider) }
                )
            }
        }
    }
}