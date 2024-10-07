package fd.f1.f1dataandroid.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.format.*
import java.time.*
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun String.toDate(withHours: Boolean): String {
    val dateFormatter = DateTimeFormatter.ISO_DATE_TIME
    val lang = Locale.getDefault().language

    val date = LocalDateTime.parse(this, dateFormatter)

    val userTimeZoneFormatter = DateTimeFormatter.ofPattern(
        if (withHours) {
            if (lang == "fr") "dd/MM/yyyy HH:mm" else "yyyy/MM/dd HH:mm"
        } else {
            if (lang == "fr") "dd/MM/yyyy" else "yyyy/MM/dd"
        }
    )

    return userTimeZoneFormatter.format(date)
}