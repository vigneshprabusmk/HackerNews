package com.hackerNewsApp.hn.data.sources.network

import com.google.gson.Gson

object GsonInstance {
  fun instance(): Gson {
    return Gson()
  }
}
