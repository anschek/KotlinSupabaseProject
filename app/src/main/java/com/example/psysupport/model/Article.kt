package com.example.psysupport.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
@SerialName("Articles")
data class Article(
    val id: Int = 0,
    @SerialName("type_id")
    val typeId:Int,
    val author:String?,
    val title:String,
    @SerialName("posting_date")
    val postingDate:String,
    val content:String
)
