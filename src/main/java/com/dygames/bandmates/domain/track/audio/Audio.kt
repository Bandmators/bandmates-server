package com.dygames.bandmates.domain.track.audio

import com.dygames.bandmates.domain.BaseEntity
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "audio_type")
abstract class Audio(
    val path: String
) : BaseEntity()
