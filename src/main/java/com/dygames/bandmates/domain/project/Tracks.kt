package com.dygames.bandmates.domain.project

import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Embeddable
class Tracks(
    @OneToMany
    val tracks: List<Track> = emptyList()
)
