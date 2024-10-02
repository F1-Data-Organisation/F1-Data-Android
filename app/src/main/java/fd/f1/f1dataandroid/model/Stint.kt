package fd.f1.f1dataandroid.model

import com.google.gson.annotations.SerializedName

data class Stint(
    @SerializedName("stint_number") val stintNumber: Int,
    @SerializedName("lap_start") val lapStart: Int,
    @SerializedName("lap_end") val lapEnd: Int,
    @SerializedName("compound") val compound: String,
    @SerializedName("tyre_age_at_start") val tyreAgeAtStart: Int
)