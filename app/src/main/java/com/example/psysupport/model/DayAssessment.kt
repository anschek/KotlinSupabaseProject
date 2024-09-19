package com.example.psysupport.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("DailyAssessments")
data class DayAssessment(
    val id: Int,
    val image: String
)
