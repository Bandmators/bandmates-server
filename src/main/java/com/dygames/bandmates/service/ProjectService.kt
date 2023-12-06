package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.project.User
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ProjectRequest
import com.dygames.bandmates.service.dto.ProjectsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {

    @Transactional
    fun create(userId: Long, request: ProjectRequest) {
        val project = Project(
            0,
            author = User(
                userId,
                request.author.name,
                request.author.email
            ),
            tracks = Tracks(emptyList())
        )
        projectRepository.save(project)
    }

    @Transactional
    fun findAll(): ProjectsResponse {
        val projects = projectRepository.findAll()
        return ProjectsResponse.of(projects)
    }

    @Transactional
    fun delete(id: Long) {
        projectRepository.deleteById(id)
    }
}
