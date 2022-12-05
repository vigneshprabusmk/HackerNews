package com.hackerNewsApp.hn.data.sources.network.source

import com.hackerNewsApp.hn.data.sources.network.ResponseWrapper
import com.hackerNewsApp.hn.data.sources.network.api.HackerNewsAPI
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.CommentModel
import javax.inject.Inject

class CommentsNetworkSource @Inject constructor(private val api: HackerNewsAPI) : BaseNetworkSource() {

  suspend fun getCommentById(id: Int): ApiResult<CommentModel> {
    return when (val fromNetwork = ResponseWrapper.safeApiCall { api.getCommentById(id) }) {
      is ResponseWrapper.GenericError -> ApiResult.ERROR(fromNetwork.error)
      ResponseWrapper.NetworkError -> ApiResult.NetworkError
      is ResponseWrapper.Success -> {
        if (fromNetwork.value.body() != null) {
          if (fromNetwork.value.body()!!.deleted) {
            val original = fromNetwork.value.body()!!
            ApiResult.OK(res = CommentModel(original.id, original.parent, "-",
                "This comment was deleted", 0, original.type, emptyList(), true))
          } else {
            ApiResult.OK(res = fromNetwork.value.body()!!)
          }
        } else {
          ApiResult.ERROR("Unable to fetch comment")
        }
      }
    }
  }
}
