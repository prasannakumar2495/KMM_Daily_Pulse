package com.pk.dailypulse.articlesSource.viewModel

import com.pk.dailypulse.BaseViewModel
import com.pk.dailypulse.articlesSource.data.ArticlesSourceState
import com.pk.dailypulse.articlesSource.repo.ArticlesSourceService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesSourceViewModel(private val articlesSourceService: ArticlesSourceService) :
	BaseViewModel() {
	private val _articlesSourceState: MutableStateFlow<ArticlesSourceState> =
		MutableStateFlow(ArticlesSourceState(loading = true))
	val articlesSourceState: StateFlow<ArticlesSourceState> = _articlesSourceState
	
	init {
		getArticlesSource()
	}
	
	private fun getArticlesSource() {
		scope.launch {
			_articlesSourceState.emit(
				ArticlesSourceState(
					loading = true,
				)
			)
			val fetchedArticles = articlesSourceService.fetchArticlesSource()
			println("Fetched Sources: ${fetchedArticles?.size}")
			if (!fetchedArticles.isNullOrEmpty())
				_articlesSourceState.emit(ArticlesSourceState(articlesSource = fetchedArticles.filterNotNull()))
			else
				_articlesSourceState.emit(ArticlesSourceState(error = "Unexpected Error!"))
		}
	}
}