package im.syf.nuice.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionArticlesDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>,
)
