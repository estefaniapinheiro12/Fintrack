package com.example.fintrack.navigation

sealed class AppDestination(val route: String) {
    object Onboarding : AppDestination("onboarding")
    object Home : AppDestination("home")
    object Login : AppDestination("login")
}
