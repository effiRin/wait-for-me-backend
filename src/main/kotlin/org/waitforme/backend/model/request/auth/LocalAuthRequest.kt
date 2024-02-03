package org.waitforme.backend.model.request.auth

data class LocalAuthRequest(
    val phoneNumber: String,
    val authText: String? = null,
)