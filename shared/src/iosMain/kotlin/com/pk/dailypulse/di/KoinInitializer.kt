package com.pk.dailypulse.di

import com.pk.dailypulse.articles.viewModel.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
	val requiredModules = sharedKoinModule + dataBaseModule
	
	startKoin {
		modules(requiredModules)
	}
}

class ArticlesInjector : KoinComponent {
	val articlesViewModel: ArticlesViewModel by inject()
}