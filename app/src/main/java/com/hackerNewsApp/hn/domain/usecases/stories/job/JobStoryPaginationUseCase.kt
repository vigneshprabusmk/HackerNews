package com.hackerNewsApp.hn.domain.usecases.stories.job

import androidx.paging.PagingData
import com.hackerNewsApp.hn.data.repository.JobStoriesRepo
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobStoryPaginationUseCase @Inject constructor(private val jobStoriesRepo: JobStoriesRepo) :
  BaseUseCase() {
  fun invoke(idsList: List<Int>): Flow<PagingData<StoryModel>> {
    return jobStoriesRepo.getPaginatedFlow(idsList)
  }
}
