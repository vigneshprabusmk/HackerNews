package com.hackerNewsApp.hn.ui.stories.ask

import androidx.lifecycle.viewModelScope
import com.hackerNewsApp.hn.domain.usecases.stories.ask.AskStoryPaginationUseCase
import com.hackerNewsApp.hn.domain.usecases.stories.ask.GetAskStoryIdsUseCase
import com.hackerNewsApp.hn.ui.base.BaseFragment.Companion.FETCHED_IDS
import com.hackerNewsApp.hn.ui.base.BaseViewModel
import com.hackerNewsApp.hn.utils.AppConstants.API_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AskStoriesViewModel @Inject constructor(private val getAskStoryIdsUseCase: GetAskStoryIdsUseCase,
  private val askStoriesPaginationUseCase: AskStoryPaginationUseCase) : BaseViewModel() {

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      postLoading()
      val response = getAskStoryIdsUseCase.invoke()
      if (response.success)
        postSuccess(response.result!!, response.message, FETCHED_IDS)
      else
        postError(response.message, API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = askStoriesPaginationUseCase.invoke(idsList)

  override fun onCleared() {
    super.onCleared()
    Timber.d("Cleared")
  }
}
