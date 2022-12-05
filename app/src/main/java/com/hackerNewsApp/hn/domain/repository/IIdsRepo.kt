package com.hackerNewsApp.hn.domain.repository

import com.hackerNewsApp.hn.domain.models.ApiResult
import com.hackerNewsApp.hn.domain.models.StoryType

interface IIdsRepo {
  suspend fun fetchStoryIds(storyType: StoryType): ApiResult<List<Int>>
}
