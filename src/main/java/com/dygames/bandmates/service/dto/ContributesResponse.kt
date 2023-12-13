package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.contribute.Contribute

data class ContributesResponse(
    val value: List<ContributeResponse>
) {
    companion object {
        fun of(contributes: List<Contribute>) = contributes.map {
            ContributeResponse.of(it)
        }.let {
            ContributesResponse(it)
        }
    }
}
