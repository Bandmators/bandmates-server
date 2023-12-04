package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ProjectsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ProjectService {
    private lateinit var projectRepository: ProjectRepository

    @Transactional
    fun findProjects(): List<ProjectsResponse> {
        return projectRepository.findAll().map {
            ProjectsResponse(
                0
            )
        }
    }
}
