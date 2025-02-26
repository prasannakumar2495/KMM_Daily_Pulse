package com.pk.dailypulse.android.di

import com.pk.dailypulse.articles.viewModel.ArticlesViewModel
import com.pk.dailypulse.articlesSource.viewModel.ArticlesSourceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
	viewModel { ArticlesViewModel(get()) }
	viewModel { ArticlesSourceViewModel(get()) }
}