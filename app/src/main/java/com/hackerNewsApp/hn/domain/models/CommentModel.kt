package com.hackerNewsApp.hn.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.hackerNewsApp.hn.data.sources.local.HackerNewsDB
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = HackerNewsDB.commentsTable)
data class CommentModel(
  @PrimaryKey @SerializedName("id") val id: Int = 0,
  @SerializedName("parent") val parent: Int = 0,
  @SerializedName("by") val by: String = "",
  @SerializedName("text") val text: String = "",
  @SerializedName("time") var time: Long? = 0,
  @SerializedName("type") val type: String = "",
  @SerializedName("kids") var kids: List<Int>?,
  @SerializedName("deleted") val deleted: Boolean = false
) : Parcelable {
  fun setDefaults() {
    if (kids == null) {
      kids = emptyList()
    }
  }
}
