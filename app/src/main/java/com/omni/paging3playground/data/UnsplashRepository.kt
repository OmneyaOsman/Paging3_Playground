package com.omni.paging3playground.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omni.paging3playground.data.api.UnsplashApi
import com.omni.paging3playground.data.paging.UnsplashPagingSource
import com.omni.paging3playground.ui.model.UnsplashPhoto
import kotlinx.coroutines.flow.Flow

class UnsplashRepository(
    private val api: UnsplashApi
) {
    fun getPhotos(): Flow<PagingData<UnsplashPhoto>> =
        Pager(
            config = PagingConfig(pageSize = 10 , enablePlaceholders = true ),
            pagingSourceFactory = { UnsplashPagingSource(api) }
        ).flow
}