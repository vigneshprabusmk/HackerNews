package com.hackerNewsApp.hn.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class StoryType(val string: String) : Parcelable {
  JOB("job"),
  SHOW("show"),
  ASK("ask"),
  TOP("top"),
}
