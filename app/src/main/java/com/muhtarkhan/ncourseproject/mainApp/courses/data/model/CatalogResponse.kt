package com.muhtarkhan.ncourseproject.mainApp.courses.data.model

import com.google.gson.annotations.SerializedName
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.model.Course
import kotlinx.serialization.Serializable

@Serializable
data class CatalogResponse(
    @SerializedName("courses")
    val courses: List<CourseDTO>,
)

@Serializable
data class CourseDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("courseDetails")
    val courseDetails: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("imageUrl")
    val imageUrl: String
){
  fun toCourse(): Course {
      return Course(id, name, description, price, duration, courseDetails, tags, imageUrl)
  }
}