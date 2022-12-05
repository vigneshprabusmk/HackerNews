package com.hackerNewsApp.hn.domain.usecases.favourites

import com.hackerNewsApp.hn.data.sources.local.source.FavouritesLocalSource
import com.hackerNewsApp.hn.domain.models.FavouriteModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(private val favLocalSource: FavouritesLocalSource) :
  BaseUseCase() {
  suspend fun invoke(): List<FavouriteModel> {
    return favLocalSource.getAll()
  }
}
