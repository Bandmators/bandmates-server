package com.dygames.bandmates.domain.repository

import com.dygames.bandmates.domain.contribute.Contribute
import com.dygames.bandmates.domain.project.Member

interface ContributeRepository : BaseRepository<Contribute, Long> {
    fun findAllByOriginOwner(member: Member): List<Contribute>
    fun findAllByForkedOwner(member: Member): List<Contribute>
}