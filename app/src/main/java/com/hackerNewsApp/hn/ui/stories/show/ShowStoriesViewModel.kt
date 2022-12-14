package com.hackerNewsApp.hn.ui.stories.show

import androidx.lifecycle.viewModelScope
import com.hackerNewsApp.hn.domain.usecases.stories.show.GetShowStoryIdsUseCase
import com.hackerNewsApp.hn.domain.usecases.stories.show.ShowStoryPaginationUseCase
import com.hackerNewsApp.hn.ui.base.BaseFragment.Companion.FETCHED_IDS
import com.hackerNewsApp.hn.ui.base.BaseViewModel
import com.hackerNewsApp.hn.utils.AppConstants.API_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowStoriesViewModel @Inject constructor(private val getShowStoryIdsUseCase: GetShowStoryIdsUseCase,
  private val showStoriesPaginationUseCase: ShowStoryPaginationUseCase) : BaseViewModel() {

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      postLoading()
      val response = getShowStoryIdsUseCase.invoke()
      if (response.success)
        postSuccess(response.result!!, response.message, FETCHED_IDS)
      else
        postError(response.message, API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = showStoriesPaginationUseCase.invoke(idsList)
}
