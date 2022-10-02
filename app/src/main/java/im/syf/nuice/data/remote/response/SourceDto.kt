package im.syf.nuice.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceDto(
    val id: String?,
    val name: String,
)
