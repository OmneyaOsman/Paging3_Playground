package com.omni.paging3playground.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omni.paging3playground.api.PhotosResponse
import com.omni.paging3playground.api.UnsplashService
import com.omni.paging3playground.model.Photo
import kotlinx.coroutines.flow.Flow


class UnsplashRepository(private val service: UnsplashService) {
    fun getSearchResultStream(query: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                GithubPagingSource(
                    service,
                    query
                )
            }).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}