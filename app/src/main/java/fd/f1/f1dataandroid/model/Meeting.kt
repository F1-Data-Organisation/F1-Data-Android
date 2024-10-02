package fd.f1.f1dataandroid.model

import com.google.gson.annotations.SerializedName

data class Meeting(
    @SerializedName("key") val key : Int,
    @SerializedName("name") val name : String,
    @SerializedName("official_name") val officialName : String,
    @SerializedName("code") val code : String,
    @SerializedName("circuit_name") val circuitName : String,
    @SerializedName("location") val location : String,
    @SerializedName("country_code") val countryCode : String,
    @SerializedName("country_name") val countryName : String,
    @SerializedName("date_start") val dateStart : String,
    @SerializedName("date_end") val dateEnd : String,
    @SerializedName("year") val year : Int
)
