package im.syf.nuice.data.remote

import android.content.Context
import co.infinum.retromock.Retromock
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

class NewsApiClient(context: Context) {

    private val baseUrl = "https://newsapi.org/v2/"

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val retromock = Retromock.Builder()
        .retrofit(retrofit)
        .defaultBodyFactory(context.assets::open)
        .build()

    val newsApiService: NewsApiService by lazy {
        retromock.create(NewsApiService::class.java)
    }
}
