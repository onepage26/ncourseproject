package com.muhtarkhan.ncourseproject.mainApp.blogs.data.repository


import com.muhtarkhan.ncourseproject.mainApp.blogs.data.api.BlogsApiService
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.model.Blogs
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.repository.BlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BlogsRepositoryImpl(private val apiService: BlogsApiService) : BlogsRepository {
    override fun getBlogs(): Flow<List<Blogs>> {
        return flow {
            val response = apiService.getBlogs()
            val blogs = response.news.map { it.toBlogs() }
            emit(blogs)
        }
    }
}