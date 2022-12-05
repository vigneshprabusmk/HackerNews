package com.hackerNewsApp.hn.domain.usecases.stories.top

import com.hackerNewsApp.hn.data.repository.IdsRepo
import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryType
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetTopStoryIdsUseCase @Inject constructor(private val idsRepo: IdsRepo) : BaseUseCase() {
  suspend fun invoke(): ApiResult<List<Int>> {
    return idsRepo.fetchStoryIds(StoryType.TOP)
  }
}
