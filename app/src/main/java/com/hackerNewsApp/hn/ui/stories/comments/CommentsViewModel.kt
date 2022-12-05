package com.hackerNewsApp.hn.ui.stories.comments

import com.hackerNewsApp.hn.domain.usecases.comments.CommentsPaginationUseCase
import com.hackerNewsApp.hn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val useCase: CommentsPaginationUseCase) : BaseViewModel() {
  fun getComments(ids: List<Int>) = useCase.invoke(ids)
}
