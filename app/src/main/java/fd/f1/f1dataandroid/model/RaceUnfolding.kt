package fd.f1.f1dataandroid.model

import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName

import fd.f1.f1dataandroid.model.RaceControl
import fd.f1.f1dataandroid.model.TeamRadio

data class RaceUnfolding(
    @SerializedName("date") val date: String,
    @SerializedName("item") val item: UnfoldingType
)

sealed class UnfoldingType {
    data class RC(val control: RaceControl) : UnfoldingType() // Remplacez par les champs réels
    data class Radio(val radio: TeamRadio) : UnfoldingType() // Remplacez par les champs réels
}