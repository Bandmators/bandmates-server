package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.track.Tracks

data class TracksResponse(
    val value: List<TrackResponse>
) {
    companion object {
        fun of(tracks: Tracks) = tracks.tracks.map {
            TrackResponse.of(it)
        }.let {
            TracksResponse(it)
        }
    }
}