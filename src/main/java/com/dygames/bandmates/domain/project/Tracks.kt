package com.dygames.bandmates.domain.project

import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Embeddable
class Tracks(
    @OneToMany
    @JoinColumn(name = "PROJECT_ID")
    val tracks: List<Track> = emptyList()
)
