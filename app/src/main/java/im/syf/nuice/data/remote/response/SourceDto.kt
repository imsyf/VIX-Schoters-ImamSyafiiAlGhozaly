package im.syf.nuice.data.remote.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SourceDto(
    val id: String?,
    val name: String,
) : Parcelable
