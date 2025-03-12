package com.muhtarkhan.ncourseproject.mainApp.courses.presentation

sealed interface CatalogEvent {
    data class OnFilterSelected(val filter: String) : CatalogEvent
    object OnLogOut : CatalogEvent
    data class SendConsultation(val name: String, val email: String, val phone: String) : CatalogEvent
}