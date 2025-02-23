package com.pk.dailypulse.articles.response

import kotlinx.serialization.Serializable

@Serializable
data class ArticleRaw(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)