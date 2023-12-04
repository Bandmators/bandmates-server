package com.dygames.bandmates.domain.project

import com.dygames.bandmates.domain.audio.Audio
import com.dygames.bandmates.domain.audio.MIDIAudio
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
class Track(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRACK_ID")
    val id: Long,

    @OneToOne
    @JoinColumn(name = "AUDIO_ID")
    val audio: Audio,

    @OneToOne
    @JoinColumn(name = "USER_ID")
    val author: User
)
