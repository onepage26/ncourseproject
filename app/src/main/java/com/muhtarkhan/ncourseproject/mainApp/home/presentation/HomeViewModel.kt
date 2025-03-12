package com.muhtarkhan.ncourseproject.mainApp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.mainApp.courses.presentation.CatalogEvent
import com.muhtarkhan.ncourseproject.mainApp.home.domain.usecase.GetHomeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getHomeUseCase: GetHomeUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    init {
        fetchMainData()
    }

    fun dispatch(event: HomeEvent, navController: NavController, accountProvider: AccountProvider){
        when(event){
            is HomeEvent.OnFilterSelected -> {
                _uiState.value = _uiState.value.copy(selectedFilter = event.filter)
            }
            is HomeEvent.onLogOut -> {
                accountProvider.clearToken()
                navController.navigate(R.id.action_homeFragment_to_loginFragment) // Навигация
            }
        }
    }

    fun fetchMainData() {
        viewModelScope.launch {
            getHomeUseCase()
                .onStart { _uiState.update { it.copy(isLoading = true, error = null) } }
                .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                .collect { data -> _uiState.update { it.copy(isLoading = false, mainData = data) } }
        }
    }
}