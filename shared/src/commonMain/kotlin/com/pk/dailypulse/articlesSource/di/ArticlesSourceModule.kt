package com.pk.dailypulse.articlesSource.di

import com.pk.dailypulse.articlesSource.repo.ArticlesSourceService
import com.pk.dailypulse.articlesSource.viewModel.ArticlesSourceViewModel
import org.koin.dsl.module

val articlesSourceModule = module {
	single<ArticlesSourceService> { ArticlesSourceService(get()) }
	single<ArticlesSourceViewModel> { ArticlesSourceViewModel(get()) }
}