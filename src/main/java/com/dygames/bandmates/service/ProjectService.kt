package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.project.repository.MemberRepository
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ProjectResponse
import com.dygames.bandmates.service.dto.ProjectsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProjectService(
    private val projectRepository: ProjectRepository, private val memberRepository: MemberRepository
) {

    @Transactional
    fun findAll(): ProjectsResponse {
        val projects = projectRepository.findAll()
        return ProjectsResponse.of(projects.toList())
    }

    @Transactional
    fun create(memberId: Long): ProjectResponse {
        val author = memberRepository.findById(memberId).get()

        val project = Project(
            author = author, owner = author, tracks = Tracks(emptyList())
        )

        return ProjectResponse.of(
            projectRepository.save(project)
        )
    }

    @Transactional
    fun delete(projectId: Long) {
        projectRepository.deleteById(projectId)
    }

    @Transactional
    fun fork(memberId: Long, projectId: Long): ProjectResponse {
        val project = projectRepository.findById(projectId).get()
        val owner = memberRepository.findById(memberId).get()

        val forkedProject = project.fork(owner)

        return ProjectResponse.of(
            projectRepository.save(forkedProject)
        )
    }

    @Transactional
    fun contribute(memberId: Long, projectId: Long): ProjectResponse {
        val project = projectRepository.findById(projectId).get()
        val originProject = projectRepository.findById(project.originId).get()

        val contributedProject = originProject.contribute(project)

        return ProjectResponse.of(
            projectRepository.save(contributedProject)
        )
    }
}
