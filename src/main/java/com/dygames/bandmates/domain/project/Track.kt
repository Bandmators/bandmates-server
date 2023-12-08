package com.dygames.bandmates.domain.project

import com.dygames.bandmates.domain.BaseEntity
import com.dygames.bandmates.domain.audio.Audio
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
class Track(
    @OneToOne
    @JoinColumn(name = "AUDIO_ID")
    val audio: Audio,

    @OneToOne
    @JoinColumn(name = "USER_ID")
    val author: User
) : BaseEntity()
