package com.hackerNewsApp.hn.di

import com.google.gson.Gson
import com.hackerNewsApp.hn.data.sources.network.GsonInstance
import com.hackerNewsApp.hn.data.sources.network.RetrofitInstance
import com.hackerNewsApp.hn.data.sources.network.api.HackerNewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  fun provideGsonInstance() = GsonInstance.instance()

  @Provides
  fun provideOkHttpClient() = RetrofitInstance.buildOkHttpClient()

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, gsonInstance: Gson) = RetrofitInstance.build(okHttpClient, gsonInstance)

  @Provides
  fun hackerNewsAPI(retrofit: Retrofit): HackerNewsAPI = retrofit.create(HackerNewsAPI::class.java)
}
