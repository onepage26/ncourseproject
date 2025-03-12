package com.muhtarkhan.ncourseproject.mainApp.courses.domain.model

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