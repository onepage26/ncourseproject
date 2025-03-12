package com.muhtarkhan.ncourseproject.mainApp.courses.presentation

import com.muhtarkhan.ncourseproject.mainApp.courses.domain.model.Course

data class CatalogUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val catalogData: List<Course> = emptyList(),
    val selectedFilter: String = "Все",
    val isConsultationSent: Boolean = false
)