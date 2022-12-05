package com.hackerNewsApp.hn.domain.usecases.favourites

import com.hackerNewsApp.hn.data.sources.local.source.FavouritesLocalSource
import com.hackerNewsApp.hn.domain.models.StoryType
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class SaveFavouriteUseCase @Inject constructor(private val local: FavouritesLocalSource) :
  BaseUseCase() {
  suspend fun invoke(id: Int, type: StoryType): Boolean {
    return local.saveOrUnSave(id, type)
  }
}
