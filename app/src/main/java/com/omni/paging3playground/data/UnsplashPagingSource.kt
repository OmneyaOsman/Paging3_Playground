package com.omni.paging3playground.data

import androidx.paging.PagingSource
import com.omni.paging3playground.api.UnsplashService
import com.omni.paging3playground.api.IN_QUALIFIER
import com.omni.paging3playground.model.Photo
import retrofit2.HttpException
import java.io.IOException


private const val GITHUB_STARTING_PAGE_INDEX = 1

class GithubPagingSource(
    private val service: UnsplashService,
    private val query: String
) : PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
//        val apiQuery = query + IN_QUALIFIER
        return try {
            val response = service.searchPhotos(query, position, params.loadSize)
            val photos = response.results
            LoadResult.Page(
                data = photos,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}