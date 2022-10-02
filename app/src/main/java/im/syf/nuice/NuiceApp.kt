package im.syf.nuice

import android.app.Application
import im.syf.nuice.data.local.BookmarkDao
import im.syf.nuice.data.local.BookmarkDatabase
import im.syf.nuice.data.remote.NewsApiClient
import im.syf.nuice.data.remote.NewsApiService

class NuiceApp : Application() {

    val newsApiService: NewsApiService by lazy {
        NewsApiClient.newsApiService
    }

    val bookmarkDao: BookmarkDao by lazy {
        BookmarkDatabase.getDatabase(this).bookmarkDao()
    }
}
