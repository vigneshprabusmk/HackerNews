package com.hackerNewsApp.hn.domain.usecases.stories.ask

import androidx.paging.PagingData
import com.hackerNewsApp.hn.data.repository.AskStoriesRepo
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AskStoryPaginationUseCase @Inject constructor(private val askStoriesRepo: AskStoriesRepo) :
  BaseUseCase() {
  fun invoke(idsList: List<Int>): Flow<PagingData<StoryModel>> {
    return askStoriesRepo.getPaginatedFlow(idsList)
  }
}
