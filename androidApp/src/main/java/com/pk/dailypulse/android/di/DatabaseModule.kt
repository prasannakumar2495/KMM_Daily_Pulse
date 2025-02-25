package com.pk.dailypulse.android.di

import app.cash.sqldelight.db.SqlDriver
import com.pk.dailypulse.articles.repo.DatabaseDriverFactory
import com.pk.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {
	single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
	single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}