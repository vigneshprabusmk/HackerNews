package com.hackerNewsApp.hn.data.sources.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertWithMessage
import com.hackerNewsApp.hn.data.sources.local.source.IdsLocalSource
import com.hackerNewsApp.hn.di.DatabaseModule
import com.hackerNewsApp.hn.domain.models.StoryIdModel
import com.hackerNewsApp.hn.domain.models.StoryType
import com.hackerNewsApp.hn.utils.DbConstants.FAKE_DB
import com.hackerNewsApp.hn.utils.SourceConstants.FAKE_LOCAL_IDS
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LocalIdsSourceTest {
  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Inject
  @Named(FAKE_DB)
  lateinit var db: HackerNewsDB

  @Inject
  @Named(FAKE_LOCAL_IDS)
  lateinit var idsLocalSource: IdsLocalSource

  private val items = mutableListOf<StoryIdModel>()

  @Before
  fun init() {
    hiltRule.inject()

    items.add(StoryIdModel(1, StoryType.SHOW))
    items.add(StoryIdModel(2, StoryType.ASK))
    items.add(StoryIdModel(3, StoryType.TOP))
    items.add(StoryIdModel(4, StoryType.SHOW))
    items.add(StoryIdModel(5, StoryType.JOB))
  }

  private fun matchItemsCount(type: StoryType, count: Int) = runBlocking {
    val items = idsLocalSource.getIdsByStoryType(type)
    assertWithMessage("There should be exactly $count story ids of type ${type.string}")
      .that(items.size)
      .isEqualTo(count)
  }

  @Test
  fun aThereShouldNotBeAnyItems() = runBlocking {
    StoryType.values().asList().forEach {
      matchItemsCount(it, 0)
    }
  }

  @Test
  fun bInsertFewStoryIds(): Unit = runBlocking {
    idsLocalSource.putIds(items)

    StoryType.values().asList().forEach { type ->
      val selected = items.filter {
        it.type == type
      }
      matchItemsCount(type, selected.count())
    }
  }

  @After
  fun tearDownDb() {
    db.close()
  }
}
