package im.syf.nuice.data.remote

import im.syf.nuice.data.remote.response.CollectionArticlesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
    ): CollectionArticlesDto
}
