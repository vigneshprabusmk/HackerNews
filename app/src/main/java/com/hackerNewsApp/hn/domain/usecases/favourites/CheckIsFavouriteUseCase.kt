package com.hackerNewsApp.hn.domain.usecases.favourites

import com.hackerNewsApp.hn.data.sources.local.source.FavouritesLocalSource
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class CheckIsFavouriteUseCase @Inject constructor(private val local: FavouritesLocalSource) :
  BaseUseCase() {
  suspend fun invoke(id: Int): Boolean {
    return local.isFavourite(id)
  }
}
