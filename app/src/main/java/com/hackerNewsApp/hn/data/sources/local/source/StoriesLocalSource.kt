package com.hackerNewsApp.hn.data.sources.local.source

import com.hackerNewsApp.hn.data.sources.local.dao.StoryDao
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.models.StoryType
import com.hackerNewsApp.hn.utils.AppDateTimeUtils
import timber.log.Timber
import javax.inject.Inject

class StoriesLocalSource @Inject constructor(private val storyDao: StoryDao) : BaseLocalSource() {
  suspend fun put(item: StoryModel, type: StoryType) {
    item.storyType = type
    item.setDefaults()
    item.title = item.title?.replace("Show HN: ", "")
      ?.replace("Ask HN: ", "")
    if (item.time != null) {
      item.time = item.time!! * AppDateTimeUtils.DEF_TIME_MULTIPLY
    } else {
      item.time = System.currentTimeMillis()
    }
    Timber.d("Storing story in localdb")
    storyDao.insert(item)
  }

  suspend fun getByShow (show: String): StoryModel? {
    return storyDao.getByShow(show)
  }

  suspend fun getByAsk (ask: String): StoryModel? {
    return storyDao.getByAsk(ask)
  }

  suspend fun getByTop (top: String): StoryModel? {
    return storyDao.getByTop(top)
  }

  suspend fun getByJob (job: String): StoryModel? {
    return storyDao.getByJob(job)
  }

  suspend fun getById(id: Int): StoryModel? {
    return storyDao.getById(id)
  }
}
