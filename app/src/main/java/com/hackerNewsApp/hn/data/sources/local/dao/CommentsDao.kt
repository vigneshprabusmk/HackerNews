package com.hackerNewsApp.hn.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hackerNewsApp.hn.domain.models.CommentModel

@Dao
interface CommentsDao : BaseDao<CommentModel> {
  @Query("SELECT * FROM comments WHERE parent = :storyId ORDER BY time DESC")
  suspend fun getByStoryId(storyId: Int): List<CommentModel>

  @Query("SELECT * FROM comments WHERE id = :id")
  suspend fun getById(id: Int): CommentModel?
}
