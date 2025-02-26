package com.pk.dailypulse.di

import com.pk.dailypulse.articles.di.articlesModule
import com.pk.dailypulse.articlesSource.di.articlesSourceModule

val sharedKoinModule = listOf(
	articlesModule, networkModule, articlesSourceModule
)