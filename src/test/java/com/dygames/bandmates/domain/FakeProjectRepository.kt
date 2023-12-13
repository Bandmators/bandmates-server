package com.dygames.bandmates.domain

import com.dygames.bandmates.domain.project.Member
import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.repository.ProjectRepository

class FakeProjectRepository : FakeRepository<Project>(), ProjectRepository {
    override fun findAllByOwner(owner: Member): List<Project> {
        return entities.filter {
            it.owner.id == owner.id
        }
    }
}