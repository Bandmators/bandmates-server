package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.track.Tracks
import com.dygames.bandmates.domain.repository.MemberRepository
import com.dygames.bandmates.domain.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ProjectResponse
import com.dygames.bandmates.service.dto.ProjectsResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun findAllByMemberId(memberId: Long): ProjectsResponse {
        val member = memberRepository.findById(memberId).get()
        val projects = projectRepository.findAllByOwner(member)
        return ProjectsResponse.of(projects)
    }

    @Transactional
    fun findById(projectId: Long): ProjectResponse {
        val project = projectRepository.findById(projectId).get()
        return ProjectResponse.of(project)
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
    fun delete(memberId: Long, projectId: Long) {
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
}
