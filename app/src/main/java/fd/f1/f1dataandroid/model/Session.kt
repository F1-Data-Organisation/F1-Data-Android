package fd.f1.f1dataandroid.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("key") val key: Int,
    @SerializedName("name") val name : String,
    @SerializedName("type") val type: String,
    @SerializedName("date_start") val dateStart: String,
    @SerializedName("date_end") val dateEnd: String,
)