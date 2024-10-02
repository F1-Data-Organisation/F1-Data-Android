package fd.f1.f1dataandroid.model

import com.google.gson.annotations.SerializedName

data class RaceControl(
    @SerializedName("date") val date: String,
    @SerializedName("lap_number") val lapNumber: Int?,
    @SerializedName("category") val category: String,
    @SerializedName("flag") val flag: String?,
    @SerializedName("scope") val scope: String?,
    @SerializedName("sector") val sector: Int?,
    @SerializedName("message") val message: String,
    @SerializedName("driver_number") val driverNumber: Int?
)

