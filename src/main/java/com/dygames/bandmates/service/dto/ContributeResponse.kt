package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.contribute.Contribute

data class ContributeResponse(
    val id: Long,
    val author: String,
    val origin: ProjectResponse,
    val forked: ProjectResponse
) {
    companion object {
        fun of(contribute: Contribute) = ContributeResponse(
            contribute.id,
            contribute.requester.name,
            ProjectResponse.of(contribute.origin),
            ProjectResponse.of(contribute.forked)
        )
    }
}