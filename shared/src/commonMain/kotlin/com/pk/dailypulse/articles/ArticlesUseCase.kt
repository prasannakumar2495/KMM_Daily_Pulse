package com.pk.dailypulse.articles

import com.pk.dailypulse.articles.response.ArticleRaw

class ArticlesUseCase(private val service: ArticlesService) {
    suspend fun getArticles(): List<Articles> {
        val articleRaw = service.fetchArticles()
        return mapArticles(articleRaw)
    }

    private fun mapArticles(articleRaw: List<ArticleRaw?>?): List<Articles> {
        return articleRaw?.map {
            Articles(
                title = it?.title ?: "",
                desc = it?.description ?: "",
                date = it?.publishedAt ?: "",
                imageUrl = it?.urlToImage ?: ""
            )
        } ?: run {
            listOf(Articles())
        }
    }
}