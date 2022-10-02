package im.syf.nuice.data.remote

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import im.syf.nuice.data.remote.response.CollectionArticlesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    @Mock
    @MockResponse(body = "top-headlines.us.json")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
    ): CollectionArticlesDto
}
