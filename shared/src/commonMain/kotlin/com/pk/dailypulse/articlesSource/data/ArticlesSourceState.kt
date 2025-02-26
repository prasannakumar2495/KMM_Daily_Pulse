package com.pk.dailypulse.articlesSource.data

import com.pk.dailypulse.articlesSource.response.Source

/**
 * Sealed classes are not yet ready for KMP
 */
data class ArticlesSourceState(
	val loading: Boolean = false,
	val articlesSource: List<Source> = emptyList(),
	val error: String? = null,
)