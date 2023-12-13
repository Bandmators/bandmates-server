package com.dygames.bandmates.domain.contribute

import com.dygames.bandmates.domain.BaseEntity
import com.dygames.bandmates.domain.project.Project
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Contribute(
    @ManyToOne
    @JoinColumn(name = "ORIGIN_ID")
    val origin: Project,

    @ManyToOne
    @JoinColumn(name = "FORKED_ID")
    val forked: Project,

    val state: ContributeState,

    id: Long = 0
) : BaseEntity(id) {

    fun accept(): Contribute {
        return Contribute(
            origin, forked, ContributeState.ACCEPTED, id
        )
    }

    fun decline(): Contribute {
        return Contribute(
            origin, forked, ContributeState.DECLINED, id
        )
    }
}

