package com.muhtarkhan.ncourseproject.mainApp.courses.data.repository

import com.muhtarkhan.ncourseproject.mainApp.courses.data.ApiService.CatalogApiService
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.model.Course
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CatalogRepositoryImp(private val apiService: CatalogApiService): CatalogRepository {
    override fun getCatalog(): Flow<List<Course>> {
        return flow {
            val response = apiService.getCatalogData()
            val catalog = response.courses.map { it.toCourse() }
            emit(catalog)
        }
    }
}