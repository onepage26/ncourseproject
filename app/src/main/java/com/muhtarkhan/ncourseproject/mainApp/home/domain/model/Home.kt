package com.muhtarkhan.ncourseproject.mainApp.home.domain.model

data class Banner(
    val imageUrl: String,
    val text: String
)

data class Course(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val duration: String,
    val courseDetails: String,
    val tags: List<String>,
    val imageUrl: String
)

data class HomeData(
    val banners: List<Banner>,
    val courses: List<Course>,
    val tags: List<String>
)
