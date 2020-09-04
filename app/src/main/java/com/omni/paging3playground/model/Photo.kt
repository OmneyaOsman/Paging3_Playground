package com.omni.paging3playground.model


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("alt_description")
    val description: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("urls")
    val urls: PhotoUrls?,
    @SerializedName("width")
    val width: Int?
)

data class PhotoUrls(
    @SerializedName("full")
    val full: String?,
    @SerializedName("raw")
    val raw: String?,
    @SerializedName("regular")
    val regular: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("thumb")
    val thumb: String?
)