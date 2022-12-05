package com.hackerNewsApp.hn.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hackerNewsApp.hn.data.paging.CommentsPagingSource
import com.hackerNewsApp.hn.data.sources.local.source.CommentsLocalSource
import com.hackerNewsApp.hn.data.sources.network.source.CommentsNetworkSource
import com.hackerNewsApp.hn.domain.DataConfig
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.CommentModel
import com.hackerNewsApp.hn.domain.repository.ICommentsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentsRepo @Inject constructor(private val commentsLocalSource: CommentsLocalSource,
  private val commentsNetworkSource: CommentsNetworkSource) : ICommentsRepo {

  override suspend fun fetchCommentById(id: Int): ApiResult<CommentModel> {
    val fromLocal = commentsLocalSource.getCommentFromDb(id)
    if (fromLocal != null) {
      return ApiResult.OK("", fromLocal)
    }

    val fromNetwork = commentsNetworkSource.getCommentById(id)
    if (fromNetwork.success)
      commentsLocalSource.storeCommentInDb(fromNetwork.result!!)
    return fromNetwork
  }

  override fun getPaginatedFlow(ids: List<Int>): Flow<PagingData<CommentModel>> = Pager(
    config = PagingConfig(
      pageSize = DataConfig.MAX_ITEMS_LIMIT,
      maxSize = DataConfig.MAX_PAGE_SIZE,
      prefetchDistance = DataConfig.PRE_FETCH_DISTANCE,
      enablePlaceholders = false),
    pagingSourceFactory = { CommentsPagingSource(::fetchCommentById, ids) }
  ).flow
}
