package com.hackerNewsApp.hn.domain.usecases.stories.show

import androidx.paging.PagingData
import com.hackerNewsApp.hn.data.repository.ShowStoriesRepo
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowStoryPaginationUseCase @Inject constructor(private val repo: ShowStoriesRepo) :
  BaseUseCase() {
  fun invoke(idsList: List<Int>): Flow<PagingData<StoryModel>> {
    return repo.getPaginatedFlow(idsList)
  }
}
