package com.example.fintrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.remote.ApiService
import com.example.fintrack.data.repository.AuthRepository
import com.example.fintrack.data.repository.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val errorDetails: List<String>? = null,
    val successMessage: String? = null
)

class RegisterViewModel : ViewModel() {

    private val apiService = ApiService.create()
    private val repository = AuthRepository(apiService)

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun registerUser(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            println("🚀 [FinTrack] Iniciando registro...")
            println("📤 [FinTrack] Dados: name=$name, email=$email")

            _uiState.value = RegisterUiState(isLoading = true)

            val result = repository.registerUser(
                fullName = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )

            println("📥 [FinTrack] Resultado recebido: ${result::class.simpleName}")

            _uiState.value = when (result) {
                is Result.Success -> {
                    println("✅ [FinTrack] Sucesso: ${result.data.message}")
                    RegisterUiState(
                        isSuccess = true,
                        successMessage = result.data.message
                    )
                }
                is Result.Error -> {
                    println("❌ [FinTrack] Erro: ${result.message}")
                    println("📋 [FinTrack] Detalhes: ${result.details}")
                    RegisterUiState(
                        errorMessage = result.message,
                        errorDetails = result.details
                    )
                }
                is Result.Loading -> {
                    RegisterUiState(isLoading = true)
                }
            }
        }
    }

    fun resetState() {
        _uiState.value = RegisterUiState()
    }

    fun checkServerHealth() {
        viewModelScope.launch {
            val result = repository.checkHealth()
            when (result) {
                is Result.Success -> {
                    println("✅ Servidor está online!")
                }
                is Result.Error -> {
                    println("❌ Servidor offline: ${result.message}")
                }
                else -> {}
            }
        }
    }
}