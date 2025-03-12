package com.muhtarkhan.ncourseproject.mainApp.home.presentation

import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.HomeData

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedFilter: String = "Все",
    val mainData: HomeData? = null
)