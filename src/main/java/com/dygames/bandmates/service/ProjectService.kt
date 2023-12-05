package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.project.User
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ProjectRequest
import com.dygames.bandmates.service.dto.ProjectsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ProjectService(
    private val projectRepository: ProjectRepository
) {

    @Transactional
    fun create(request: ProjectRequest) {
        val project = Project(0, User(0, "", ""), Tracks(emptyList()))
        projectRepository.save(project)
    }

    @Transactional
    fun findAll(): ProjectsResponse {
        return ProjectsResponse.of(
            projectRepository.findAll()
        )
    }
}
