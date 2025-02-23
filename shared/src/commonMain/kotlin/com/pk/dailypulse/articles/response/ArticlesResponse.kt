package com.pk.dailypulse.articles.response

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    val articles: List<ArticleRaw?>?,
    val status: String?,
    val totalResults: Int?
)