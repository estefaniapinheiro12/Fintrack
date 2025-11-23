package com.example.fintrack.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fintrack.screen.OnboardingScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppDestination.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Onboarding Screen
        composable(AppDestination.Onboarding.route) {
            OnboardingScreen(
                onGetStarted = {
                    navController.navigate(AppDestination.Home.route) {
                        popUpTo(AppDestination.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable(AppDestination.Home.route) {
            // HomeScreen(navController = navController)
            // Placeholder temporário - substitua pela sua HomeScreen
            androidx.compose.foundation.layout.Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                androidx.compose.material3.Text(
                    text = "Home Screen - Suas finanças aqui!",
                    style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
                )
            }
        }

        // Login Screen
        composable(AppDestination.Login.route) {
            // LoginScreen(navController = navController)
        }
    }
}
