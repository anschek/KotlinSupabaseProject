package com.example.psysupport.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("DailyMoods")
data class DailyMood(
    val id:Int = 0,
    @SerialName("user_id")
    val userId: String,
    @SerialName("note_date")
    val noteDate: String,
    @SerialName("emotion_id")
    val emotionId: Int,
    val note:String?,
    @SerialName("daily_assessment_id")
    val dailyAssessmentId: Int?
)
