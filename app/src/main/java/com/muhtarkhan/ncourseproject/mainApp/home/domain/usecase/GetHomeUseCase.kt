package com.muhtarkhan.ncourseproject.mainApp.home.domain.usecase

import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.HomeData
import com.muhtarkhan.ncourseproject.mainApp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetHomeUseCase(private val repository: HomeRepository) {
    operator fun invoke(): Flow<HomeData> = repository.getHomeData()
}
