package com.muhtarkhan.ncourseproject.mainApp.home.data.model

import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.Banner
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.Course
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.HomeData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponse(
    @SerialName("banners")
    val banners: List<BannerDto>,
    @SerialName("courses")
    val courses: List<CourseDto>,
    @SerialName("tags")
    val tags: List<String>
) {
    fun toDomain(): HomeData {
        return HomeData(
            banners = banners.map { it.toDomain() },
            courses = courses.map { it.toDomain() },
            tags = tags
        )
    }
}

@Serializable
data class BannerDto(
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("text")
    val text: String
) {
    fun toDomain(): Banner = Banner(imageUrl, text)
}

@Serializable
data class CourseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Int,
    @SerialName("duration")
    val duration: String,
    @SerialName("courseDetails")
    val courseDetails: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("imageUrl")
    val imageUrl: String
) {
    fun toDomain(): Course = Course(id, name, description, price, duration, courseDetails, tags, imageUrl)
}



