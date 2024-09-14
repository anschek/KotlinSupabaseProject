package com.example.psysupport.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("MoodTypes")
data class MoodType(
    @SerialName("mood_type_id")
    val id:Int,
    @SerialName("mood_type_name")
    val moodName: String
)
