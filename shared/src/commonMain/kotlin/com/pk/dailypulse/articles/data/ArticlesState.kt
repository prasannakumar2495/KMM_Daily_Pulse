package com.pk.dailypulse.articles.data

/**
 * Sealed classes are not yet ready for KMP
 */
data class ArticlesState(
	val loading: Boolean = false,
	val articles: List<Articles> = emptyList(),
	val error: String? = null
)