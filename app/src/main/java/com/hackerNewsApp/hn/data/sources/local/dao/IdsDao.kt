package com.hackerNewsApp.hn.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hackerNewsApp.hn.domain.models.StoryIdModel
import com.hackerNewsApp.hn.domain.models.StoryType

@Dao
interface IdsDao : BaseDao<StoryIdModel> {
  @Query("SELECT * FROM ids WHERE type = :type ORDER BY birth DESC")
  suspend fun getAllIdsByType(type: StoryType): List<StoryIdModel>
}
