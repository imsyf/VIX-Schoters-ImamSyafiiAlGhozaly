package im.syf.nuice.data.remote.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
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
) : Parcelable
