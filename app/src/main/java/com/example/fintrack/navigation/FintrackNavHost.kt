package com.example.fintrack.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fintrack.screen.LoginScreen
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
                    navController.navigate(AppDestination.Login.route) {
                        popUpTo(AppDestination.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable(AppDestination.Login.route) {
            LoginScreen(
                onLoginClick = { email, password ->
                    // TODO: Implementar lógica de login
                    // Por enquanto, navega direto para Home
                    navController.navigate(AppDestination.Home.route) {
                        popUpTo(AppDestination.Login.route) { inclusive = true }
                    }
                },
                onGoogleClick = {
                    // TODO: Implementar login com Google
                },
                onFacebookClick = {
                    // TODO: Implementar login com Facebook
                },
                onRegisterClick = {
                    // TODO: Navegar para tela de cadastro
                    // navController.navigate(AppDestination.Register.route)
                },
                onForgotPasswordClick = {
                    // TODO: Navegar para recuperação de senha
                    // navController.navigate(AppDestination.ForgotPassword.route)
                }
            )
        }

        // Home Screen
        composable(AppDestination.Home.route) {
            // HomeScreen(navController = navController)
            // Placeholder temporário - substitua pela HomeScreen
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
    }
}

