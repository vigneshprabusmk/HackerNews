package com.hackerNewsApp.hn.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hackerNewsApp.hn.data.sources.local.HackerNewsDB.Companion.idsTable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = idsTable)
data class StoryIdModel(
  @PrimaryKey val id: Int,
  val type: StoryType,
  val birth: Long = System.currentTimeMillis()
) : Parcelable
