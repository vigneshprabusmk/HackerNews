package com.hackerNewsApp.hn.domain.usecases.stories.top

import com.hackerNewsApp.hn.data.repository.TopStoriesRepo
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetTopStoryUseCase @Inject constructor(private val topStoriesRepo: TopStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return topStoriesRepo.fetchItemById(id)
  }
}
