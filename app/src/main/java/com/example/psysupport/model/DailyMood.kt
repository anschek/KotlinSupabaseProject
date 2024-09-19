package com.example.psysupport.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("DailyMoods")
data class DailyMood(
    val id:Int,
    @SerialName("user_id")
    val userId:Int,
    @SerialName("note_date")
    val noteDate:LocalDate,
    @SerialName("emotion_id")
    val emotionId: Int,
    val note:String?,
    @SerialName("daily_assessment_id")
    val dailyAssessmentId: Int
)
