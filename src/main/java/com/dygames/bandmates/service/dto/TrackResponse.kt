package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.project.Track

data class TrackResponse(
    val id: Long
) {
    companion object {
        fun of(track: Track) = TrackResponse(
            track.id
        )
    }
}