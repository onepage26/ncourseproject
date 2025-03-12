package com.muhtarkhan.ncourseproject.mainApp.blogs.domain.usecase

import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.model.Blogs
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.repository.BlogsRepository
import kotlinx.coroutines.flow.Flow



class GetBlogsUseCase(private val repository: BlogsRepository) {
    operator fun invoke(): Flow<List<Blogs>> {
        return repository.getBlogs()
    }
}