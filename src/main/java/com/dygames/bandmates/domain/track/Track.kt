package com.dygames.bandmates.domain.track

import com.dygames.bandmates.domain.BaseEntity
import com.dygames.bandmates.domain.track.audio.Audio
import com.dygames.bandmates.domain.member.Member
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
class Track(
    @OneToOne
    @JoinColumn(name = "AUDIO_ID")
    val audio: Audio,

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    val author: Member
) : BaseEntity()
