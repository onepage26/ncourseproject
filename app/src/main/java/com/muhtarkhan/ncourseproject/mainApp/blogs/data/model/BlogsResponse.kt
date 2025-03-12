package com.muhtarkhan.ncourseproject.mainApp.blogs.data.model

import com.google.gson.annotations.SerializedName
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.model.Blogs
import kotlinx.serialization.Serializable

@Serializable
data class BlogsResponse(
    @SerializedName("news")
    val news: List<BlogsDto>
)

@Serializable
data class BlogsDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("fullText")
    val fullText: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("imageUrl") val imageUrl: String
) {
    fun toBlogs(): Blogs {
        return Blogs(id, title, shortDescription, fullText, tags, imageUrl)
    }
}

