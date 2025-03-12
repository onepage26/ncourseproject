package com.muhtarkhan.ncourseproject.mainApp.blogs.presentation

import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.model.Blogs

data class BlogsUIState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val blogsData: List<Blogs>? = null
)