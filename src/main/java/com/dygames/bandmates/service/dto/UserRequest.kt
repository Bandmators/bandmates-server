package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.project.User

data class UserRequest(
    val id: Long,
    val name: String,
    val email: String,
) {
    companion object {
        fun of(user: User) = UserRequest(
            user.id,
            user.name,
            user.email
        )
    }
}
