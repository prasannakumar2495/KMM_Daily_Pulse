package com.pk.dailypulse.articles.di

import com.pk.dailypulse.articles.ArticlesService
import com.pk.dailypulse.articles.ArticlesUseCase
import com.pk.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}