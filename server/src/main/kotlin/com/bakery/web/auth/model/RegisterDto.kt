package com.bakery.web.auth.model

import kotlinx.serialization.SerialName

data class RegisterDto(
    @SerialName("name")
    val name: String,
    @SerialName("lastname")
    val lastname: String,
    @SerialName("email")
    val email: String,
    @SerialName("birth_date")
    val birthDate: String,
    @SerialName("phone")
    val phone: String,
)
