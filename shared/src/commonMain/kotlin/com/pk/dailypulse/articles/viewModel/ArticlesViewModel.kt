package com.pk.dailypulse.articles.viewModel

import com.pk.dailypulse.BaseViewModel
import com.pk.dailypulse.articles.data.ArticlesState
import com.pk.dailypulse.articles.useCase.ArticlesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ArticlesViewModel(private val useCase: ArticlesUseCase) : BaseViewModel() {
	private val _articlesState: MutableStateFlow<ArticlesState> =
		MutableStateFlow(ArticlesState(loading = true))
	val articlesState: StateFlow<ArticlesState> = _articlesState
	
	init {
		getArticles()
	}
	
	fun getArticles(forceFetch: Boolean = false) {
		scope.launch {
			try {
				_articlesState.emit(
					ArticlesState(
						loading = true,
						articles = _articlesState.value.articles
					)
				)
				delay(1.seconds)
				val fetchedArticles = useCase.getArticles(forceFetch)
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