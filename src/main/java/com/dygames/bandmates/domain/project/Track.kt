package com.dygames.bandmates.domain.project

import jakarta.persistence.*

@Entity
class Track(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    @Column
    val audio: Audio = MIDIAudio(),

    @OneToOne
    @Column
    val author: User = User()
)
