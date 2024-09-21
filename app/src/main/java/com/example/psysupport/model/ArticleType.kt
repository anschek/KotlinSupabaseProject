package com.example.psysupport.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ArticleTypes")
data class ArticleType(
    val id:Int,
    val name: String
)
