package com.hackerNewsApp.hn.ui.adapters.stories

import com.hackerNewsApp.hn.domain.models.StoryModel

interface StoryItemClickListener {
  fun onClick(item: StoryModel)

  fun onClickError()
}
