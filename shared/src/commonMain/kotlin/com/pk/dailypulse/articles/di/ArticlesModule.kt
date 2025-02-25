package com.pk.dailypulse.articles.di

import com.pk.dailypulse.articles.repo.ArticlesDataSource
import com.pk.dailypulse.articles.repo.ArticlesRepo
import com.pk.dailypulse.articles.repo.ArticlesService
import com.pk.dailypulse.articles.useCase.ArticlesUseCase
import com.pk.dailypulse.articles.viewModel.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
	single<ArticlesService> { ArticlesService(get()) }
	single<ArticlesUseCase> { ArticlesUseCase(get()) }
	single<ArticlesViewModel> { ArticlesViewModel(get()) }
	single<ArticlesDataSource> { ArticlesDataSource(get()) }
	single<ArticlesRepo> { ArticlesRepo(get(), get()) }
}