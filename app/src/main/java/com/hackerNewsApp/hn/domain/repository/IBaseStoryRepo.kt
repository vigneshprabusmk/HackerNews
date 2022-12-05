package com.hackerNewsApp.hn.domain.repository

import androidx.paging.PagingData
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.models.StoryType
import kotlinx.coroutines.flow.Flow

interface IBaseStoryRepo {
  suspend fun fetchItemById(id: Int): ApiResult<StoryModel>

  fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>>

  suspend fun fetchItemByIdAndType(id: Int, type: StoryType): ApiResult<StoryModel>
}
