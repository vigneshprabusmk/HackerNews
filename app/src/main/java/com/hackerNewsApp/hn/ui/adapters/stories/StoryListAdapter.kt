package com.hackerNewsApp.hn.ui.adapters.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackerNewsApp.hn.databinding.LayoutStoryItemBinding
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.utils.AppDateTimeUtils
import java.util.*

class StoryListAdapter constructor(private val listener: StoryItemClickListener) :
                                   //private var Storylist: MutableList<StoryModel>?) :
  PagingDataAdapter<StoryModel, StoryListAdapter.StoryViewHolder>(StoryDiffUtilCallBack()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
    return from(parent)
  }

  open fun updateList(list: MutableList<StoryModel>) {
   // Storylist = list
    notifyDataSetChanged()
  }

  fun from(parent: ViewGroup): StoryViewHolder {
    return StoryViewHolder(
      LayoutStoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
    holder.bind(getItem(position)!!)
  //  val data = Storylist?.get(position)
  //  println("abc1"+data)
   // data?.let { holder.bind(it) }
  }



  inner class StoryViewHolder constructor
    (private val binding: LayoutStoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
      binding.parentLayout.setOnClickListener {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
          val item = getItem(position)
          if (item != null) {
            listener.onClick(item)
            return@setOnClickListener
          }
        }
        // if didn't return call error
        listener.onClickError()
      }
    }

    fun bind(story: StoryModel) {
      binding.apply {
        authorNameTextView.text = story.by
        titleTextView.text = story.title
//        commentsImageView.icon =
//          AppIcons.fawComment(binding.root.context)
//        scoreImageView.icon =
//          AppIcons.fawHeart(binding.root.context)
        scoreTextView.text = story.score.toString()
        commentsTextView.text = story.kids?.size.toString()
        storyTypeTextView.text = story.storyType.string.uppercase()
        timeTextView.text = AppDateTimeUtils.whenDidThisHappen(story.time)
      }
    }
  }
}
