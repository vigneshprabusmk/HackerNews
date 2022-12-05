package com.hackerNewsApp.hn.data.sources.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.hackerNewsApp.hn.data.sources.local.source.StoriesLocalSource
import com.hackerNewsApp.hn.di.DatabaseModule
import com.hackerNewsApp.hn.domain.models.StoryModel
import com.hackerNewsApp.hn.domain.models.StoryType
import com.hackerNewsApp.hn.utils.DbConstants
import com.hackerNewsApp.hn.utils.SourceConstants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LocalStorySourceTest {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Inject
  @Named(DbConstants.FAKE_DB)
  lateinit var db: HackerNewsDB

  @Inject
  @Named(SourceConstants.FAKE_LOCAL_STORIES)
  lateinit var storiesLocalSource: StoriesLocalSource

  @Before
  fun init() {
    hiltRule.inject()
  }

  @Test
  fun storiesTest() = runBlocking {
    for (i in 0..6) {
      val id = Random.nextInt(0, 100)
      val randomTypeIndex = Random.nextInt(0, StoryType.values().size)
      val type = StoryType.values()[randomTypeIndex]

      storiesLocalSource.put(StoryModel(id, "story"), type)

      val story = storiesLocalSource.getById(id)
      assertThat(story).isNotEqualTo(null)
      assertThat(story?.id).isEqualTo(id)
      assertThat(story?.storyType).isEqualTo(type)
    }
  }

  @After
  fun tearDownDb() {
    db.close()
  }
}
