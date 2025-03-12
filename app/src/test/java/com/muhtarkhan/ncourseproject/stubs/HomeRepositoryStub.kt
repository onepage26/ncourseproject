package com.muhtarkhan.ncourseproject.stubs

import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.HomeData
import com.muhtarkhan.ncourseproject.mainApp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryStub(
    private val homeData: HomeData? = null,
    private val exception: Exception? = null
) : HomeRepository {

    override fun getHomeData(): Flow<HomeData> {
        return flow {
            if (exception != null) throw exception
            emit(homeData!!)
        }
    }
}