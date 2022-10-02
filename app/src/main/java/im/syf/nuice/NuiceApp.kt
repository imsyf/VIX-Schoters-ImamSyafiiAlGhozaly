package im.syf.nuice

import android.app.Application
import im.syf.nuice.data.remote.NewsApiClient
import im.syf.nuice.data.remote.NewsApiService

class NuiceApp : Application() {

    val newsApiService: NewsApiService by lazy {
        NewsApiClient.newsApiService
    }
}
