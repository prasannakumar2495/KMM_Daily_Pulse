package com.pk.dailypulse.articlesSource.repo

import com.pk.dailypulse.articlesSource.response.ArticlesSource
import com.pk.dailypulse.articlesSource.response.Source
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesSourceService(private val httpClient: HttpClient) {
	private val apiKey = "d816e6c7b0cb4187bddc9c9e50329ab6"
	
	suspend fun fetchArticlesSource(): List<Source?>? {
		val response: ArticlesSource? =
			httpClient.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey")
				.body()
		return response?.sources
	}
}