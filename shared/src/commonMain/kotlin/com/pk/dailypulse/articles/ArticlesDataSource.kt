package com.pk.dailypulse.articles

import com.pk.dailypulse.articles.response.ArticleRaw
import com.pk.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {
	fun getAllArticles(): List<ArticleRaw> {
		return database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw)
			.executeAsList()
	}
	
	private fun mapToArticleRaw(
		title: String,
		desc: String?,
		date: String,
		url: String?,
	): ArticleRaw {
		return ArticleRaw(
			title = title, description = desc,
			publishedAt = date, urlToImage = url,
			author = null, content = null, url = null,
			source = null
		)
	}
}