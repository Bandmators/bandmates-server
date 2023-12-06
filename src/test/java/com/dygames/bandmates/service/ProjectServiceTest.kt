package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.project.User
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ProjectRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ProjectServiceTest {

    @Mock
    private lateinit var projectRepository: ProjectRepository

    @Test
    fun 프로젝트를_생성한다() {
        val projectService = ProjectService(projectRepository)

        val sut = Project(0, User(0, "", ""), Tracks(emptyList()))

        projectService.create(
            2,
            ProjectRequest.of(sut)
        )
    }
}