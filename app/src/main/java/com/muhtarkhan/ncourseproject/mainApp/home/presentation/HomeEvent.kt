package com.muhtarkhan.ncourseproject.mainApp.home.presentation

sealed interface HomeEvent {
    data class OnFilterSelected(val filter: String): HomeEvent
    object onLogOut : HomeEvent
}