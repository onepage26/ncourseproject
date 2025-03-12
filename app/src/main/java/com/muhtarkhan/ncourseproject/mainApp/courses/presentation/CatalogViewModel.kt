package com.muhtarkhan.ncourseproject.mainApp.courses.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.usecase.GetCatalogUseCase
import com.muhtarkhan.ncourseproject.mainApp.courses.presentation.consultationImpl.ConsultationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(private val getCatalogUseCase: GetCatalogUseCase, private val repository: ConsultationRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogUiState(isLoading = true))
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    init {
        fetchCatalogData()
    }

    fun dispatch(event: CatalogEvent, navController: NavController, accountProvider: AccountProvider) {
        when (event) {
            is CatalogEvent.OnLogOut -> {
                accountProvider.clearToken()
                navController.navigate(R.id.action_catalogFragment_to_loginFragment)
            }
            is CatalogEvent.OnFilterSelected -> {
                _uiState.value = _uiState.value.copy(selectedFilter = event.filter)
            }
            is CatalogEvent.SendConsultation -> {
                viewModelScope.launch {
                    val success = repository.sendConsultationRequest(event.name, event.email, event.phone)
                    _uiState.update { it.copy(isConsultationSent = success) }
                }
            }
        }
    }

    private fun fetchCatalogData() {
        viewModelScope.launch {
            getCatalogUseCase()
                .onEach { blogs ->
                    _uiState.value = CatalogUiState(isLoading = false, catalogData = blogs)
                }
                .catch { error ->
                    _uiState.value = CatalogUiState(isLoading = false, error = error.message)
                }
                .launchIn(viewModelScope)
        }
    }
}
