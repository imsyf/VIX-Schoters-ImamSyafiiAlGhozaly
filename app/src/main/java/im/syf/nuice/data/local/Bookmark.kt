package im.syf.nuice.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import im.syf.nuice.data.remote.response.ArticleDto
import im.syf.nuice.data.remote.response.SourceDto
import im.syf.nuice.ext.timeAgo
import java.util.Date

@Entity(tableName = "bookmark")
data class Bookmark(
    @PrimaryKey val url: String,
    val title: String,
    val author: String?,
    val urlToImage: String?,
    val description: String?,
    val content: String?,
    val timeAgo: String,
) {
    companion object {
        fun fromArticle(article: ArticleDto): Bookmark = Bookmark(
            article.url,
            article.title,
            article.author,
            article.urlToImage,
            article.description,
            article.content,
            article.publishedAt.timeAgo
        )
    }

    fun toArticle(): ArticleDto = ArticleDto(
        url,
        title,
        author,
        urlToImage,
        description,
        content,
        Date(),
        SourceDto(null, "")
    )
}
