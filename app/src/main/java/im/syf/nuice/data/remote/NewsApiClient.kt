package im.syf.nuice.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import im.syf.nuice.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

object NewsApiClient {

    private const val baseUrl = "https://newsapi.org/v2/"

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    private val auth = Interceptor { chain ->
        val request = chain.request().newBuilder()
            // Add NEWSAPI_KEY="your api key here" in local.properties
            .addHeader("X-Api-Key", BuildConfig.NEWSAPI_KEY)
            .build()

        chain.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(auth)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val newsApiService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}
