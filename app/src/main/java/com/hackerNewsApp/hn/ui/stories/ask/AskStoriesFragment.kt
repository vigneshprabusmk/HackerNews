package com.hackerNewsApp.hn.ui.stories.ask

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.hackerNewsApp.hn.data.sources.local.HackerNewsDB
import com.hackerNewsApp.hn.databinding.FragmentAskStoriesBinding
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.ui.adapters.loading.LoadingIndicatorAdapter
import com.hackerNewsApp.hn.ui.adapters.stories.StoryListAdapter
import com.hackerNewsApp.hn.ui.base.BaseFragment
import com.hackerNewsApp.hn.utils.ViewEvent
import com.hackerNewsApp.hn.utils.extensions.showHide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class AskStoriesFragment : BaseFragment() {

  private val viewModel: AskStoriesViewModel by viewModels()
  private lateinit var binding: FragmentAskStoriesBinding
  private lateinit var adapter: StoryListAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentAskStoriesBinding.inflate(layoutInflater)

    setBinding(binding)

    registerViewEvents()
    addObservers()
    Timber.d("OnCreateView")

    binding.refreshLayout.setOnRefreshListener {
      binding.refreshLayout.isRefreshing = false
      // on below line we are shuffling our list using random
      // Collections.shuffle(courseList, Random(System.currentTimeMillis()))
      viewModel.pullData()
      registerViewEvents()
      addObservers()
      adapter.notifyDataSetChanged()
    }

    binding.SVFilterSearchView.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
      override fun afterTextChanged(s: Editable) {
        filter(s.toString())
      }
    })

    return binding.root
  }

  fun filter(text: String) {
    val temp: MutableList<StoryModel> = ArrayList()
    for (`object` in temp) {
      if (`object`.title?.lowercase(Locale.ROOT).toString().contains(text)||
        (`object`.title?.lowercase(Locale.ROOT).toString().contains(text))) {
        temp.add(`object`)
      }
    }
    adapter.updateList(temp)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    Timber.d("onDestroyView")
  }

  override fun registerViewEvents() {
    adapter = StoryListAdapter(this)
    setupRecyclerView()

    binding.errorViewLayout.retryButton.setOnClickListener {
      viewModel.pullData()
    }
  }

  private fun setupRecyclerView() {
    binding.apply {
      recyclerView.setHasFixedSize(true)
      recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
        header = LoadingIndicatorAdapter { adapter.retry() },
        footer = LoadingIndicatorAdapter { adapter.retry() }
      )
    }
  }

  override fun addObservers() {
    viewModel.viewEvent.observe(viewLifecycleOwner) {
      handleViewEvents(it)
    }
    listenToLoadingStates()
  }

  private fun showHideLoader(show: Boolean) = binding.progressBar.showHide(show)

  private fun showHideErrorViews(show: Boolean) = binding.errorViewLayout.root.showHide(show)

  private fun handleViewEvents(viewEvent: ViewEvent) {
    when (viewEvent) {
      is ViewEvent.Error<*> -> {
        binding.errorViewLayout.errorTextView.text = viewEvent.error
        showHideLoader(false)
        showHideErrorViews(true)
      }
      ViewEvent.Idle -> {
        showHideLoader(false)
      }
      ViewEvent.Loading -> {
        showHideErrorViews(false)
        showHideLoader(true)
      }
      is ViewEvent.Success<*> -> {
        when (viewEvent.code) {
          FETCHED_IDS -> {
            showToast(viewEvent.message)
            listenToPaginationFlow(viewEvent.data as List<Int>)
            showHideErrorViews(false)
          }
        }
      }
    }
  }

  private fun listenToLoadingStates() {
    adapter.addLoadStateListener { loadStates ->
      binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
      if (loadStates.refresh is LoadState.Error) {
        showHideErrorViews(true)
        binding.errorViewLayout.errorTextView.text =
          (loadStates.refresh as LoadState.Error).error.localizedMessage
      } else {
        showHideErrorViews(false)
      }
    }
  }

  private fun listenToPaginationFlow(ids: List<Int>) {
    lifecycleScope.launch {
      binding.progressBar.visibility = View.GONE
      viewModel.getPaginatedFlow(ids).collect {
        adapter.submitData(it)
      }
    }
  }
}
