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
    val author: Member,

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    val owner: Member,

    @Embedded
    @JoinColumn(name = "TRACKS_ID")
    val tracks: Tracks,

    val originId: Long = 0,
    id: Long = 0
) : BaseEntity(id) {

    fun fork(newOwner: Member): Project {
        return Project(
            author, newOwner, tracks, id
        )
    }

    fun merge(project: Project): Project {
        return Project(
            author, author, tracks.merge(project.tracks), id, id
        )
    }
}
