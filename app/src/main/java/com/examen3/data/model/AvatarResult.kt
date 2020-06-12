package com.examen3.data.model
import com.google.gson.annotations.SerializedName

data class AvatarResult(
    @SerializedName("face")
    val face: Face
)

data class Face(
    @SerializedName("eyes")
    val eyes: List<String>,
    @SerializedName("mouth")
    val mouth: List<String>,
    @SerializedName("nose")
    val nose: List<String>
)