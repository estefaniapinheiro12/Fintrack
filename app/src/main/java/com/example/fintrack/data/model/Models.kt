package com.example.fintrack.data.model

import com.google.gson.annotations.SerializedName

data class UserRegistrationRequest(
    @SerializedName("fullName")
    val fullName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("confirmPassword")
    val confirmPassword: String
)

data class UserResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("fullName")
    val fullName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("createdAt")
    val createdAt: String
)

data class SuccessResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("user")
    val user: UserResponse? = null
)

data class ErrorResponse(
    @SerializedName("error")
    val error: String,

    @SerializedName("details")
    val details: List<String>? = null
)

data class LoginRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)

data class LoginResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("token")
    val token: String? = null,

    @SerializedName("user")
    val user: UserResponse? = null
)