package com.dygames.bandmates.domain.contribute

import com.dygames.bandmates.domain.BaseEntity
import com.dygames.bandmates.domain.project.Member
import com.dygames.bandmates.domain.project.Project
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Contribute(
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    val requester: Member,

    @ManyToOne
    @JoinColumn(name = "ORIGIN_ID")
    val origin: Project,

    @ManyToOne
    @JoinColumn(name = "FORKED_ID")
    val forked: Project,

    val state: ContributeState
) : BaseEntity() {

    fun accept(): Contribute {
        return Contribute(
            requester, origin, forked, ContributeState.ACCEPTED
        )
    }

    fun decline(): Contribute {
        return Contribute(
            requester, origin, forked, ContributeState.DECLINED
        )
    }
}

