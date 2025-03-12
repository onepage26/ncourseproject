package com.muhtarkhan.ncourseproject.mainApp.home.domain.repository

import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.HomeData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getHomeData(): Flow<HomeData>
}
