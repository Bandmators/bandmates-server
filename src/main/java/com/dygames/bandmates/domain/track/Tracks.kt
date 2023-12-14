package com.dygames.bandmates.domain.track

import jakarta.persistence.Embeddable
import jakarta.persistence.OneToMany

@Embeddable
class Tracks(
    @OneToMany
    val tracks: List<Track>
) {
    fun merge(tracks: Tracks): Tracks {
        return Tracks(this.tracks + tracks.tracks)
    }
}
