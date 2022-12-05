package com.hackerNewsApp.hn.data.repository

import androidx.paging.PagingData
import com.hackerNewsApp.hn.data.sources.local.source.StoriesLocalSource
import com.hackerNewsApp.hn.data.sources.network.source.StoriesNetworkSource
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.models.StoryType
import com.hackerNewsApp.hn.domain.repository.IBaseStoryRepo
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

abstract class BaseStoryRepo(private val storiesNetworkSource: StoriesNetworkSource,
  private val storyLocalSource: StoriesLocalSource) : IBaseStoryRepo {

  abstract override suspend fun fetchItemById(id: Int): ApiResult<StoryModel>
  abstract override fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>>

  override suspend fun fetchItemByIdAndType(id: Int, type: StoryType): ApiResult<StoryModel> {
    val fromLocal = fetchItemByIdFromLocalDb(id)
    if (fromLocal != null) {
      Timber.d("Item exists in localdb $fromLocal")
      return ApiResult.OK(res = fromLocal)
    }

    val fromNetwork = fetchItemByIdFromNetwork(id)
    if (fromNetwork.success)
      storeItemInDb(fromNetwork.result!!, type)

    return fromNetwork
  }

  private suspend fun fetchItemByIdFromLocalDb(id: Int): StoryModel? {
    return storyLocalSource.getById(id)
  }

  private suspend fun fetchItemByShowFromLocalDb(show: String): StoryModel? {
    return storyLocalSource.getByShow(show)
  }

  private suspend fun fetchItemByAskFromLocalDb(ask: String): StoryModel? {
    return storyLocalSource.getByAsk(ask)
  }

  private suspend fun fetchItemByTopFromLocalDb(top: String): StoryModel? {
    return storyLocalSource.getByTop(top)
  }

  private suspend fun fetchItemByJobFromLocalDb(job: String): StoryModel? {
    return storyLocalSource.getByJob(job)
  }

  private suspend fun fetchItemByIdFromNetwork(id: Int): ApiResult<StoryModel> {
    return storiesNetworkSource.getStoryById(id)
  }

  private suspend fun storeItemInDb(item: StoryModel, type: StoryType) {
    storyLocalSource.put(item, type)
  }
}
