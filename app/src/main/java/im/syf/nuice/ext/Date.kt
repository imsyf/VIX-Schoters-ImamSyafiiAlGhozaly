package im.syf.nuice.ext

import android.text.format.DateUtils
import java.util.Calendar
import java.util.Date

val Date.timeAgo: String
    get() = DateUtils.getRelativeTimeSpanString(
        time,
        Calendar.getInstance().timeInMillis,
        DateUtils.MINUTE_IN_MILLIS
    ).toString()
