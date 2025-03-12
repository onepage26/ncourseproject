package com.muhtarkhan.ncourseproject.mainApp.courses.domain.usecase

import com.muhtarkhan.ncourseproject.mainApp.courses.domain.model.Course
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow

class GetCatalogUseCase(private val repository: CatalogRepository) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getCatalog()
    }
}