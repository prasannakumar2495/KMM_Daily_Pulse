package com.pk.dailypulse.android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pk.dailypulse.android.screens.AboutScreen
import com.pk.dailypulse.android.screens.ArticlesScreen
import com.pk.dailypulse.android.screens.Screen
import com.pk.dailypulse.articles.ArticlesViewModel

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ARTICLES.route,
        modifier = modifier
    ) {
        composable(Screen.ARTICLES.route) {
            ArticlesScreen(
                modifier = Modifier
            ) {
                navController.navigate(Screen.ABOUT_DEVICE.route)
            }
        }
        composable(Screen.ABOUT_DEVICE.route) {
            AboutScreen { navController.popBackStack() }
        }
    }
}