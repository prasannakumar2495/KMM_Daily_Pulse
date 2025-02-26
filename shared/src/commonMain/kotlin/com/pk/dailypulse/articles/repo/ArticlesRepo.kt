package com.pk.dailypulse.articles.repo

import com.pk.dailypulse.articles.response.ArticleRaw

class ArticlesRepo(
	private val dataSource: ArticlesDataSource,
	private val service: ArticlesService,
) {
	suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
		if (forceFetch) {
			dataSource.clearArticles()
			return fetchArticles()
		}
		val articlesDb = dataSource.getAllArticles()
		println("Fetched Details Size: ${articlesDb.size}")
		
		if (articlesDb.isEmpty()) {
			return fetchArticles()
		}
		
		return articlesDb
	}
	
	private suspend fun fetchArticles(): List<ArticleRaw> {
		val fetchedArticles = service.fetchArticles()?.filterNotNull()
		return fetchedArticles?.let {
			dataSource.insertArticles(it)
			it
		} ?: run {
			emptyList()
		}
	}
}