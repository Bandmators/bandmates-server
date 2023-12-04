package com.dygames.bandmates.domain.audio

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
@DiscriminatorValue("Wave")
class WaveAudio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long
) : Audio()