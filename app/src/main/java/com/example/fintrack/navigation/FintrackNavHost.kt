package com.example.fintrack.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fintrack.data.remote.ApiService
import com.example.fintrack.data.repository.AuthRepository
import com.example.fintrack.data.repository.Result
import com.example.fintrack.ui.screen.HomeScreen
import com.example.fintrack.ui.screen.LoginScreen
import com.example.fintrack.ui.screen.OnboardingScreen
import com.example.fintrack.ui.screen.RegisterScreen
import com.example.fintrack.ui.viewmodel.LoginViewModel

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
            val context = LocalContext.current

            // Cria o ViewModel
            val viewModel: LoginViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return LoginViewModel(AuthRepository(ApiService.create())) as T
                    }
                }
            )

            val loginState by viewModel.loginState.collectAsState()

            // Observa o estado do login
            LaunchedEffect(loginState) {
                when (val state = loginState) {
                    is Result.Success -> {
                        Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(AppDestination.Home.route) {
                            popUpTo(AppDestination.Login.route) { inclusive = true }
                        }
                        viewModel.resetState()
                    }

                    is Result.Error -> {
                        Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                        viewModel.resetState()
                    }

                    is Result.Loading -> {
                        // Loading ativo
                    }

                    null -> {
                        // Estado inicial
                    }
                }
            }

            LoginScreen(
                onLoginClick = { email, password ->
                    viewModel.login(email, password)
                },
                onGoogleClick = {
                    Toast.makeText(context, "Login com Google em breve", Toast.LENGTH_SHORT).show()
                },
                onFacebookClick = {
                    Toast.makeText(context, "Login com Facebook em breve", Toast.LENGTH_SHORT)
                        .show()
                },
                onRegisterClick = {
                    navController.navigate(AppDestination.Register.route)
                },
                onForgotPasswordClick = {
                    Toast.makeText(context, "Recuperação de senha em breve", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }

        // Register Screen
        composable(AppDestination.Register.route) {
            RegisterScreen(
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        // Home Screen
        // Home Screen
        composable(AppDestination.Home.route) {
            HomeScreen()
        }
    }

}

