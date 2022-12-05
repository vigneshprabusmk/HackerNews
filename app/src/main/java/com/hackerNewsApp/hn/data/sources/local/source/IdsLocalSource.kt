package com.hackerNewsApp.hn.data.sources.local.source

import com.hackerNewsApp.hn.data.sources.local.dao.IdsDao
import com.hackerNewsApp.hn.domain.models.StoryIdModel
import com.hackerNewsApp.hn.domain.models.StoryType
import javax.inject.Inject

class IdsLocalSource @Inject constructor(private val idsDao: IdsDao) : BaseLocalSource() {
  suspend fun getIdsByStoryType(type: StoryType): List<StoryIdModel> {
    return idsDao.getAllIdsByType(type)
  }

  suspend fun putIds(list: List<StoryIdModel>) {
    idsDao.insertAll(list)
  }
}
