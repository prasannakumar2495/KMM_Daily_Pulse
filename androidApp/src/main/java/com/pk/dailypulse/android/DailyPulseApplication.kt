package com.pk.dailypulse.android

import android.app.Application
import com.pk.dailypulse.android.di.dataBaseModule
import com.pk.dailypulse.android.di.viewModelsModule
import com.pk.dailypulse.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		
		initKoin()
	}
	
	private fun initKoin() {
		val requiredModules = sharedKoinModule + viewModelsModule + dataBaseModule
		startKoin {
			androidContext(this@DailyPulseApplication)
			modules(requiredModules)
		}
	}
}