package com.hackerNewsApp.hn.data.sources.network.source

import com.hackerNewsApp.hn.data.sources.network.ResponseWrapper
import com.hackerNewsApp.hn.data.sources.network.api.HackerNewsAPI
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryModel
import javax.inject.Inject

class StoriesNetworkSource @Inject constructor(private val api: HackerNewsAPI) : BaseNetworkSource() {

  suspend fun getStoryById(id: Int): ApiResult<StoryModel> {
    return when (val fromNetwork = ResponseWrapper.safeApiCall { api.getStoryById(id) }) {
      is ResponseWrapper.GenericError -> ApiResult.ERROR(fromNetwork.error)
      ResponseWrapper.NetworkError -> ApiResult.NetworkError
      is ResponseWrapper.Success -> {
        if (fromNetwork.value.body() != null) {
          ApiResult.OK(res = fromNetwork.value.body()!!)
        } else {
          ApiResult.ERROR("Unable to fetch data")
        }
      }
    }
  }
}
