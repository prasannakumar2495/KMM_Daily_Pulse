package com.pk.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.pk.dailypulse.articles.repo.DatabaseDriverFactory
import com.pk.dailypulse.db.DailyPulseDatabase
import org.koin.dsl.module

val dataBaseModule = module {
	single<SqlDriver> { DatabaseDriverFactory().createDriver() }
	single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}