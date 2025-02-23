package com.pk.dailypulse.di

import com.pk.dailypulse.articles.di.articlesModule

val sharedKoinModule = listOf(
    articlesModule, networkModule
)