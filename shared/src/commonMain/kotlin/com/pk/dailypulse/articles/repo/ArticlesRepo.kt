package com.pk.dailypulse.articles.repo

import com.pk.dailypulse.articles.response.ArticleRaw

class ArticlesRepo(
	private val dataSource: ArticlesDataSource,
	private val service: ArticlesService,
) {
	suspend fun getArticles(): List<ArticleRaw> {
		val articlesDb = dataSource.getAllArticles()
		println("Fetched Details Size: ${articlesDb.size}")
		
		if (articlesDb.isEmpty()) {
			val fetchedArticles = service.fetchArticles()?.filterNotNull()
			fetchedArticles?.let {
				dataSource.insertArticles(it)
				return it
			}
		}
		
		return articlesDb
	}
}