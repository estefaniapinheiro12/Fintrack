package com.example.fintrack.data.repository

import com.example.fintrack.data.model.ErrorResponse
import com.example.fintrack.data.model.LoginRequest
import com.example.fintrack.data.model.LoginResponse
import com.example.fintrack.data.model.SuccessResponse
import com.example.fintrack.data.model.UserRegistrationRequest
import com.example.fintrack.data.remote.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String, val details: List<String>? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

class AuthRepository(private val apiService: ApiService) {

    suspend fun registerUser(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Result<SuccessResponse> = withContext(Dispatchers.IO) {
        try {
            val request = UserRegistrationRequest(
                fullName = fullName,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )

            val response = apiService.register(request)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error("Resposta vazia do servidor")
            } else {
                // Tenta extrair a mensagem de erro do corpo da resposta
                val errorBody = response.errorBody()?.string()
                val errorResponse = try {
                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                } catch (e: Exception) {
                    null
                }

                Result.Error(
                    message = errorResponse?.error ?: "Erro ao registrar usuário",
                    details = errorResponse?.details
                )
            }
        } catch (e: Exception) {
            Result.Error(
                message = when (e) {
                    is java.net.UnknownHostException -> "Sem conexão com o servidor"
                    is java.net.SocketTimeoutException -> "Tempo de conexão esgotado"
                    is java.net.ConnectException -> "Não foi possível conectar ao servidor"
                    else -> e.message ?: "Erro desconhecido"
                }
            )
        }
    }

    suspend fun checkHealth(): Result<Map<String, Any>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.checkHealth()

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error("Resposta vazia do servidor")
            } else {
                Result.Error("Servidor indisponível")
            }
        } catch (e: Exception) {
            Result.Error("Erro ao verificar saúde do servidor: ${e.message}")
        }
    }

    suspend fun loginUser(
        email: String,
        password: String
    ): Result<LoginResponse> = withContext(Dispatchers.IO) {
        try {
            val request = LoginRequest(
                email = email,
                password = password
            )

            val response = apiService.login(request)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error("Resposta vazia do servidor")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = try {
                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                } catch (e: Exception) {
                    null
                }

                Result.Error(
                    message = errorResponse?.error ?: "Email ou senha incorretos",
                    details = errorResponse?.details
                )
            }
        } catch (e: Exception) {
            Result.Error(
                message = when (e) {
                    is java.net.UnknownHostException -> "Sem conexão com o servidor"
                    is java.net.SocketTimeoutException -> "Tempo de conexão esgotado"
                    is java.net.ConnectException -> "Não foi possível conectar ao servidor"
                    else -> e.message ?: "Erro desconhecido"
                }
            )
        }
    }
}