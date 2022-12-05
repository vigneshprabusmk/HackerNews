package com.hackerNewsApp.hn.domain.usecases.comments

import androidx.paging.PagingData
import com.hackerNewsApp.hn.data.repository.CommentsRepo
import com.hackerNewsApp.hn.domain.models.CommentModel
import com.hackerNewsApp.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentsPaginationUseCase @Inject constructor(private val repo: CommentsRepo) :
  BaseUseCase() {
  fun invoke(ids: List<Int>): Flow<PagingData<CommentModel>> {
    return repo.getPaginatedFlow(ids)
  }
}
