package com.pk.dailypulse.articlesSource.response

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesSource(
	val sources: List<Source?>?,
	val status: String?,
)