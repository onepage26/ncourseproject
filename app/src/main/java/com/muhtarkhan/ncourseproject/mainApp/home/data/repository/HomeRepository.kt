package com.muhtarkhan.ncourseproject.mainApp.home.data.repository

import com.muhtarkhan.ncourseproject.mainApp.home.data.api.ApiService
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.HomeData
import com.muhtarkhan.ncourseproject.mainApp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(private val apiService: ApiService) : HomeRepository {
    override fun getHomeData(): Flow<HomeData> = flow {
        val response = apiService.getMainData()
        emit(response.toDomain())
    }
}
