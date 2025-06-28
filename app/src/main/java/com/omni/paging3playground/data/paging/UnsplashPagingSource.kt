package com.omni.paging3playground.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omni.paging3playground.data.api.UnsplashApi
import com.omni.paging3playground.data.model.toDomain
import com.omni.paging3playground.ui.model.UnsplashPhoto
import retrofit2.HttpException
import java.io.IOException


private const val GITHUB_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val api: UnsplashApi,
) : PagingSource<Int, UnsplashPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val page = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val photos = api.getPhotos(page).map { it.toDomain() }
            LoadResult.Page(
                data = photos,
                prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? =
        state.anchorPosition?.let { state.closestPageToPosition(it)?.nextKey?.minus(1) }
}