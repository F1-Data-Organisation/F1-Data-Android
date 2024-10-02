package fd.f1.f1dataandroid.model

import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("driver_number") val number: Int,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("name_acronym") val nameAcronym: String,
    @SerializedName("team_name") val teamName: String?,
    @SerializedName("team_colour") val teamColour: String?,
    @SerializedName("headshot_url") val headshotUrl: String?, // Utilisation de String pour l'URL
    @SerializedName("country_code") val countryCode: String?
)