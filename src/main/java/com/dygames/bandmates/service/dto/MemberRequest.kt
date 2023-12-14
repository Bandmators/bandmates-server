package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.member.Member

data class MemberRequest(
    val id: Long,
    val name: String,
    val email: String,
) {
    companion object {
        fun of(member: Member) = MemberRequest(
            member.id,
            member.name,
            member.email
        )
    }
}
