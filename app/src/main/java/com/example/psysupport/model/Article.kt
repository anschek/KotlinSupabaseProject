package com.example.psysupport.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Articles")
data class Article(
    val id: Int,
    @SerialName("type_id")
    val typeId:Int,
    val author:String?,
    val title:String,
    @SerialName("posting_date")
    val postingDate:String,
    val content:String
)
