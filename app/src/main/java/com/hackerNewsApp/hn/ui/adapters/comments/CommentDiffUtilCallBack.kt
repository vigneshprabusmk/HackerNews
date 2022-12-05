package com.hackerNewsApp.hn.ui.adapters.comments

import androidx.recyclerview.widget.DiffUtil
import com.hackerNewsApp.hn.domain.models.CommentModel

class CommentDiffUtilCallBack : DiffUtil.ItemCallback<CommentModel>() {
  override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
    return oldItem == newItem
  }
}
