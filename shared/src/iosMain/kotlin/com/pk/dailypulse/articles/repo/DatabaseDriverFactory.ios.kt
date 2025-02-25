package com.pk.dailypulse.articles.repo

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.pk.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory {
	actual fun createDriver(): SqlDriver {
		return NativeSqliteDriver(
			schema = DailyPulseDatabase.Schema,
			name = "DailyPulseDatabase.db"
		)
	}
}