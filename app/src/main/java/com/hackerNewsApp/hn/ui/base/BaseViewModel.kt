package com.hackerNewsApp.hn.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackerNewsApp.hn.utils.AppConstants.INTERNET_ERROR
import com.hackerNewsApp.hn.utils.ViewEvent
import com.hackerNewsApp.hn.utils.error
import com.hackerNewsApp.hn.utils.idle
import com.hackerNewsApp.hn.utils.load
import com.hackerNewsApp.hn.utils.success

open class BaseViewModel : ViewModel() {
  private val _viewEvent = MutableLiveData<ViewEvent>()
  val viewEvent: LiveData<ViewEvent> = _viewEvent

  fun postNetworkError() {
    _viewEvent.error(INTERNET_ERROR, BaseFragment.NETWORK_ERROR, null)
  }

  fun postLoading() {
    _viewEvent.load()
  }

  fun postIdle() {
    _viewEvent.idle()
  }

  fun postSuccess(data: Any?, message: String, code: Int) {
    _viewEvent.success(data, message, code)
  }

  fun postError(error: String, code: Int, data: Any?) {
    _viewEvent.error(error, code, data)
  }
}
