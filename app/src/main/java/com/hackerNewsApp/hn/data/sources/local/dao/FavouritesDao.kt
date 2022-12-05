package com.hackerNewsApp.hn.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hackerNewsApp.hn.domain.models.FavouriteModel
import com.hackerNewsApp.hn.domain.models.StoryType

@Dao
interface FavouritesDao : BaseDao<FavouriteModel> {
  @Query("SELECT * FROM favourites WHERE type = :type ORDER BY savedOn DESC")
  suspend fun getAllByType(type: StoryType): List<FavouriteModel>

  @Query("SELECT * FROM favourites WHERE id = :id")
  suspend fun getById(id: Int): FavouriteModel?

  @Query("SELECT * FROM favourites ORDER BY savedOn DESC")
  suspend fun getAll(): List<FavouriteModel>
}
