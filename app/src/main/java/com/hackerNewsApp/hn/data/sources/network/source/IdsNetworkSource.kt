package com.hackerNewsApp.hn.data.sources.network.source

import com.hackerNewsApp.hn.data.sources.network.ResponseWrapper
import com.hackerNewsApp.hn.data.sources.network.api.HackerNewsAPI
import com.hackerNewsApp.hn.data.sources.network.response.IdsResponse
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryIdModel
import com.hackerNewsApp.hn.domain.models.StoryType
import retrofit2.Response
import javax.inject.Inject

class IdsNetworkSource @Inject constructor(private val api: HackerNewsAPI) : BaseNetworkSource() {

  suspend fun getIdsByType(storyType: StoryType): ApiResult<List<Int>> {
    val idsList = mutableSetOf<Int>()

    return when (val networkResponse = apiCallByStoryType(storyType)) {
      is ResponseWrapper.GenericError ->
        ApiResult.ERROR(networkResponse.error + ", no data available in cached storage")
      ResponseWrapper.NetworkError ->
        ApiResult.NetworkError
      is ResponseWrapper.Success -> {
        if (networkResponse.value.body() != null) {
          val list = mutableListOf<StoryIdModel>()
          val currentTimestamp = System.currentTimeMillis()
          for (i in networkResponse.value.body()!!) {
            idsList.add(i)
            list.add(StoryIdModel(i, storyType, currentTimestamp))
          }
          ApiResult.OK("", idsList.toList())
        } else {
          ApiResult.ERROR("No data found")
        }
      }
    }
  }

  private suspend fun apiCallByStoryType(storyType: StoryType):
    ResponseWrapper<Response<IdsResponse>> {
    return ResponseWrapper.safeApiCall {
      when (storyType) {
        StoryType.JOB -> api.getJobStories()
        StoryType.SHOW -> api.getShowStories()
        StoryType.ASK -> api.getAskStories()
        StoryType.TOP -> api.getTopStories()
      }
    }
  }
}
