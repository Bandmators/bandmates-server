package com.dygames.bandmates.domain.track.audio

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("MIDI")
class MIDIAudio : Audio()