package com.pk.dailypulse.articlesSource.response

import kotlinx.serialization.Serializable

@Serializable
data class Source(
	val category: String?,
	val country: String?,
	val description: String?,
	val id: String?,
	val language: String?,
	val name: String?,
	val url: String?,
)