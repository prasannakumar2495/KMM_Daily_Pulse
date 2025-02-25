package com.pk.dailypulse.articles.repo

import com.pk.dailypulse.articles.response.ArticleRaw
import com.pk.dailypulse.articles.response.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"
    private val apiKey = "d816e6c7b0cb4187bddc9c9e50329ab6"

    suspend fun fetchArticles(): List<ArticleRaw?>? {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()
        return response.articles
    }
}