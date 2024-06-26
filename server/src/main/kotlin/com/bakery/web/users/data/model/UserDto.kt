package com.bakery.web.users.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int? = null,
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
    @SerialName("created_at")
    val createdAt: String = "",
)
