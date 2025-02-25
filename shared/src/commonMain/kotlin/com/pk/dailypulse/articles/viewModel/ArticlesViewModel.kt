package com.pk.dailypulse.articles.viewModel

import com.pk.dailypulse.BaseViewModel
import com.pk.dailypulse.articles.ArticlesState
import com.pk.dailypulse.articles.useCase.ArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(private val useCase: ArticlesUseCase) : BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            try {
                val fetchedArticles = useCase.getArticles()
                if (fetchedArticles.isNotEmpty())
                    _articlesState.emit(ArticlesState(articles = fetchedArticles))
                else
                    _articlesState.emit(ArticlesState(error = "Unexpected Error!"))
            } catch (e: Exception) {
                println("EXCEPTION: " + e.message)
            }
        }
    }
}