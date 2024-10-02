package fd.f1.f1dataandroid.model

import com.google.gson.annotations.SerializedName

data class TeamRadio(
    @SerializedName("date") val date: String,
    @SerializedName("driver") val driver: Driver,
    @SerializedName("recording_url") val recordingURL: String
)