package com.hackerNewsApp.hn.ui.base

import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.models.StoryType
import com.hackerNewsApp.hn.ui.adapters.stories.StoryItemClickListener
import com.hackerNewsApp.hn.ui.stories.ask.AskStoriesFragmentDirections
import com.hackerNewsApp.hn.ui.stories.detail.StoryDetailedActivity
import timber.log.Timber

abstract class BaseFragment : Fragment(), StoryItemClickListener {

  private lateinit var binding: ViewBinding

  abstract fun registerViewEvents()
  abstract fun addObservers()

  fun setBinding(b: ViewBinding) {
    binding = b
  }

  private fun showError(msg: String) {
    Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
  }

  override fun onClick(item: StoryModel) {
    Timber.d("$item ")

    if (item.storyType == StoryType.ASK) {
      val action = AskStoriesFragmentDirections.actionAskStoriesToAskStoryDetailFragment(item)
      findNavController().navigate(action)
      return
    }

    if (item.url == null) {
      showError("Url or webpage for the story was removed")
      return
    }
    StoryDetailedActivity.open(requireActivity(), item)
  }

  override fun onClickError() {
    showError("Unable to open story")
  }

  fun showToast(msg: String) {
    if (msg.isBlank()) return
    val centeredText: Spannable = SpannableString(msg)
    centeredText.setSpan(
      AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
      0, msg.length - 1,
      Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )
    Toast.makeText(requireContext(), centeredText, Toast.LENGTH_SHORT).show()
  }

  companion object {
    const val NETWORK_ERROR: Int = 2000
    const val FETCHED_IDS: Int = 1
  }
}
