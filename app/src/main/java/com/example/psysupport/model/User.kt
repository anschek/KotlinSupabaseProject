package com.example.psysupport.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Users")
data class User(
    val id:String,
    val firstname:String?,
    val secondname:String?,
    val role:String,
    @SerialName("birth_date")
    val birthDate: LocalDate
)
