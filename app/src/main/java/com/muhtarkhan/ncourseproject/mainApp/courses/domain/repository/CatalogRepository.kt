package com.muhtarkhan.ncourseproject.mainApp.courses.domain.repository

import com.muhtarkhan.ncourseproject.mainApp.courses.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    fun getCatalog(): Flow<List<Course>>
}