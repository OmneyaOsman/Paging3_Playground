package com.omni.paging3playground.data.model

import com.omni.paging3playground.ui.model.UnsplashPhoto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashDto(
    val id:        String,
    val urls:      UrlsDto,
    val user:      UserDto,
)
@JsonClass(generateAdapter = true)
data class UrlsDto(val small: String)
@JsonClass(generateAdapter = true)
data class UserDto(val name: String)

fun UnsplashDto.toDomain() = UnsplashPhoto(
    id   = id,
    url  = urls.small,
    user = user.name
)