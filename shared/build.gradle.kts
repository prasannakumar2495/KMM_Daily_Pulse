plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidLibrary)
	id("co.touchlab.skie").version("0.10.1")
	kotlin("plugin.serialization").version("2.1.10")
	alias(libs.plugins.sqlDelight)
}

kotlin {
	androidTarget {
		compilations.all {
			kotlinOptions {
				jvmTarget = "1.8"
			}
		}
	}
	
	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach {
		it.binaries.framework {
			baseName = "shared"
			isStatic = true
		}
	}
	
	sourceSets {
		commonMain.dependencies {
			implementation(libs.kotlinx.coroutines.core)
			implementation(libs.ktor.client.core)
			implementation(libs.ktor.client.content.negotiation)
			implementation(libs.ktor.serialization.kotlinx.json)
			implementation(libs.kotlinx.datetime)
			implementation(libs.koin.core)
			implementation(libs.sql.coroutines.extensions)
			implementation("co.touchlab:stately-common:1.2.0")
		}
		androidMain.dependencies {
			implementation(libs.androidx.lifecycle.viewmodel.ktx)
			implementation(libs.ktor.client.android)
			implementation(libs.sql.android.driver)
		}
		iosMain.dependencies {
			implementation(libs.ktor.client.darwin)
			implementation(libs.sql.native.driver)
			implementation(libs.stately.isolate)
			implementation(libs.stately.iso.collections)
		}
		commonTest.dependencies {
			implementation(libs.kotlin.test)
		}
	}
}

android {
	namespace = "com.pk.dailypulse"
	compileSdk = 34
	defaultConfig {
		minSdk = 24
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
}

sqldelight {
	databases {
		create(name = "DailyPulseDatabase") {
			packageName.set("com.pk.dailypulse.db")
		}
	}
}