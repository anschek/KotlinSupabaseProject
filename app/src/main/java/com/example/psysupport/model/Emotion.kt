package com.example.psysupport.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Emotions")
data class Emotion(
    @SerialName("emotion_id")
    val id: Int,
    @SerialName("mood_type_id")
    val moodTypeId: Int,
    @SerialName("emotion_name")
    val emotionName:String
)
