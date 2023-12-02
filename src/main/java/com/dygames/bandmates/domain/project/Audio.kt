package com.dygames.bandmates.domain.project

import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "audio_type")
interface Audio {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long
}

@Entity
@DiscriminatorValue("MIDI")
class MIDIAudio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0
) : Audio {

}
