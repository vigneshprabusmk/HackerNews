package com.hackerNewsApp.hn.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hackerNewsApp.hn.domain.models.StoryModel

@Dao
interface StoryDao : BaseDao<StoryModel> {
  @Query("SELECT * FROM stories WHERE id = :id")
  suspend fun getById(id: Int): StoryModel?

  @Query("SELECT * FROM stories WHERE type = :show")
  fun getByShow(show: String): StoryModel?

  @Query("SELECT * FROM stories WHERE type = :ask")
  fun getByAsk(ask: String): StoryModel?

  @Query("SELECT * FROM stories WHERE type = :top")
  fun getByTop(top: String): StoryModel?

  @Query("SELECT * FROM stories WHERE type = :job")
  fun getByJob(job: String): StoryModel?
}
