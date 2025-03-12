package com.muhtarkhan.ncourseproject.mainApp.blogs.domain.model

data class Blogs(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val fullText: String,
    val tags: List<String>,
    val imageUrl: String
)