package com.hackerNewsApp.hn.domain.usecases.stories.ask

import com.hackerNewsApp.hn.data.repository.AskStoriesRepo
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetAskStoryUseCase @Inject constructor(private val askStoriesRepo: AskStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return askStoriesRepo.fetchItemById(id)
  }
}
