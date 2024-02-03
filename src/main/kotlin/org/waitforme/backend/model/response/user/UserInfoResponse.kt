package org.waitforme.backend.model.response.user

import org.waitforme.backend.entity.user.User
import org.waitforme.backend.enums.GenderType
import org.waitforme.backend.enums.Provider
import java.time.LocalDateTime

data class UserInfoResponse(
    val id: Int = 0,
    val provider: Provider,
    val phoneNumber: String,
    val email: String? = null,
    val name: String,
    val birthedAt: LocalDateTime? = null,
    val gender: GenderType? = null,
    val profileImage: String? = null,
    val isOwner: Boolean = false,
    val isAuth: Boolean = false, // 인증 여부, sns로 등록 시 자동 인증, local은 회원 가입 시 인증 절차 필요
    val isAdult: Boolean = false,
)

fun User.toUserInfoResponse() = UserInfoResponse(
    id = id,
    provider = provider,
    phoneNumber = phoneNumber,
    email = email,
    name = name,
    birthedAt = birthedAt,
    gender = gender,
    profileImage = profileImage,
    isOwner = isOwner,
    isAuth = isAuth,
    isAdult = isAdult
)