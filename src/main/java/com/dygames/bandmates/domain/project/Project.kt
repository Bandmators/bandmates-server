package com.dygames.bandmates.domain.project

import com.dygames.bandmates.domain.BaseEntity
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Project(
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    val author: User,

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    val owner: User,

    @Embedded
    @JoinColumn(name = "TRACKS_ID")
    val tracks: Tracks
) : BaseEntity()
