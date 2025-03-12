package com.muhtarkhan.ncourseproject.mainApp.blogs.domain.repository

import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.model.Blogs
import kotlinx.coroutines.flow.Flow

interface BlogsRepository {
    fun getBlogs(): Flow<List<Blogs>>
}