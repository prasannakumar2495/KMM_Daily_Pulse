package com.pk.dailypulse.articles.repo

import com.pk.dailypulse.articles.response.ArticleRaw
import com.pk.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {
	fun getAllArticles(): List<ArticleRaw> {
		return database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw)
			.executeAsList()
	}
	
	fun insertArticles(articlesRaw: List<ArticleRaw>) {
		database.dailyPulseDatabaseQueries.transaction {
			articlesRaw.forEach { singleArticle ->
				insertArticle(singleArticle)
			}
		}
	}
	
	fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()
	
	private fun insertArticle(singleArticle: ArticleRaw) {
		database.dailyPulseDatabaseQueries.insertArticle(
			title = singleArticle.title ?: "",
			desc = singleArticle.description,
			date = singleArticle.publishedAt ?: "",
			imageUrl = singleArticle.urlToImage
		)
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