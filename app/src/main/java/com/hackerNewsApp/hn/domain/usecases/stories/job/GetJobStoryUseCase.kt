package com.hackerNewsApp.hn.domain.usecases.stories.job

import com.hackerNewsApp.hn.data.repository.JobStoriesRepo
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetJobStoryUseCase @Inject constructor(private val jobStoriesRepo: JobStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return jobStoriesRepo.fetchItemById(id)
  }
}
