package com.pk.dailypulse.articles.useCase

import com.pk.dailypulse.articles.data.Articles
import com.pk.dailypulse.articles.repo.ArticlesRepo
import com.pk.dailypulse.articles.response.ArticleRaw
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(private val repo: ArticlesRepo) {
	suspend fun getArticles(forceFetch: Boolean): List<Articles> {
		val articleRaw = repo.getArticles(forceFetch)
		return mapArticles(articleRaw)
	}
	
	private fun mapArticles(articleRaw: List<ArticleRaw?>?): List<Articles> {
		return articleRaw?.map {
			Articles(
				title = it?.title ?: "",
				desc = it?.description ?: "",
				date = getDaysAgoString(it?.publishedAt ?: ""),
				imageUrl = it?.urlToImage ?: ""
			)
		} ?: run {
			listOf(Articles())
		}
	}
	
	private fun getDaysAgoString(date: String): String {
		val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
		val days = today.daysUntil(
			Instant.parse(date)
				.toLocalDateTime(TimeZone.currentSystemDefault()).date
		)
		return when {
			abs(days) > 1 -> "${abs(days)} days ago"
			abs(days) == 1 -> "Yesterday"
			else -> "Today"
		}
	}
}