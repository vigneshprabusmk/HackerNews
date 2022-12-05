package com.hackerNewsApp.hn.domain.repository

import androidx.paging.PagingData
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.CommentModel
import kotlinx.coroutines.flow.Flow

interface ICommentsRepo {
  suspend fun fetchCommentById(id: Int): ApiResult<CommentModel>

  fun getPaginatedFlow(ids: List<Int>): Flow<PagingData<CommentModel>>
}
