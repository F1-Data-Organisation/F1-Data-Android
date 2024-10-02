package fd.f1.f1dataandroid.model

import com.google.gson.annotations.SerializedName

data class LapData(
    @SerializedName("driver") val driver: Driver?,
    @SerializedName("lap_number") val lapNumber: Int,
    @SerializedName("date") val date: String,
    @SerializedName("duration") val duration: Double,
    @SerializedName("is_pit_out_lap") val isPitOutLap: Boolean,
    @SerializedName("sector_1") val sector1: Double,
    @SerializedName("sector_2") val sector2: Double,
    @SerializedName("sector_3") val sector3: Double
)