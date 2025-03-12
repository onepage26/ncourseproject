package com.muhtarkhan.ncourseproject.mainApp.feedback.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.mainApp.home.presentation.HomeEvent
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.usecase.GetFeedbackUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedbackViewModel(private val getFeedbackUseCase: GetFeedbackUseCase) : ViewModel() {


    private val _uiState = MutableStateFlow(FeedbackUiState(isLoading = true))
    val uiState: StateFlow<FeedbackUiState> = _uiState.asStateFlow()

    init {
        fetchFeedback()
    }

    fun dispatch(event: FeedbackEvent, navController: NavController, accountProvider: AccountProvider){
        when(event){
            is FeedbackEvent.onLogOut -> {
                accountProvider.clearToken()
                navController.navigate(R.id.action_feedback_to_loginFragment) // Навигация
            }
        }
    }

    private fun fetchFeedback() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                getFeedbackUseCase().collect { response ->
                    _uiState.update { it.copy(isLoading = false, feedbackData = response) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}