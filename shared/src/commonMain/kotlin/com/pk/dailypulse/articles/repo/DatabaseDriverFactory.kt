package com.pk.dailypulse.articles.repo

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
	fun createDriver(): SqlDriver
}