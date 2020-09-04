package com.omni.paging3playground.api


import com.google.gson.annotations.SerializedName
import com.omni.paging3playground.model.Photo

data class PhotosResponse(
    @SerializedName("results") val results: List<Photo> = emptyList(),
    @SerializedName("total") val total: Int = 0,
    @SerializedName("total_pages") val totalPages: Int?,
    val nextPage: Int? = null
)