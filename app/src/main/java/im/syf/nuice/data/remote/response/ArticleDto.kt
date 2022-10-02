package im.syf.nuice.data.remote.response

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class ArticleDto(
    val url: String,
    val title: String,
    val author: String?,
    val urlToImage: String?,
    val description: String?,
    val content: String?,
    val publishedAt: Date,
    val source: SourceDto,
)
