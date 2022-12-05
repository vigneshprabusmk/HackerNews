package com.hackerNewsApp.hn.domain.usecases.stories.top

import androidx.paging.PagingData
import com.hackerNewsApp.hn.data.repository.TopStoriesRepo
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopStoryPaginationUseCase @Inject constructor(private val topStoriesRepo: TopStoriesRepo) :
  BaseUseCase() {
  fun invoke(idsList: List<Int>): Flow<PagingData<StoryModel>> {
    return topStoriesRepo.getPaginatedFlow(idsList)
  }
}
