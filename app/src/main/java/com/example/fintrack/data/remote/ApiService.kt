package com.example.fintrack.data.remote

import com.example.fintrack.data.model.LoginRequest
import com.example.fintrack.data.model.LoginResponse
import com.example.fintrack.data.model.SuccessResponse
import com.example.fintrack.data.model.UserRegistrationRequest
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/")
    suspend fun getRoot(): Response<String>

    @GET("/health")
    suspend fun checkHealth(): Response<Map<String, Any>>

    @POST("/api/auth/register")
    suspend fun register(@Body request: UserRegistrationRequest): Response<SuccessResponse>

    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    companion object {
        private const val BASE_URL = "https://fintrack-backend-u84d.onrender.com/"

        fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS) 
                .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}