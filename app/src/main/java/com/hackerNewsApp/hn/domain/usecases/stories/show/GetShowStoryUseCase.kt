package com.hackerNewsApp.hn.domain.usecases.stories.show

import com.hackerNewsApp.hn.data.repository.ShowStoriesRepo
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetShowStoryUseCase @Inject constructor(private val showStoriesRepo: ShowStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return showStoriesRepo.fetchItemById(id)
  }
}
