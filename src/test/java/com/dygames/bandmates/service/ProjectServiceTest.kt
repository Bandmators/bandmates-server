package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.project.User
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import com.dygames.bandmates.domain.project.repository.UserRepository
import com.dygames.bandmates.service.dto.ProjectRequest
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProjectServiceTest {

    @Autowired
    private lateinit var projectRepository: ProjectRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    private lateinit var projectService: ProjectService

    @BeforeEach
    fun setUp() {
        projectService = ProjectService(projectRepository, userRepository)
    }

    @Test
    fun 프로젝트를_생성한다() {
        // given
        val author = User("", "")
        val sut = Project(author, author, Tracks(emptyList()))

        // when
        projectService.create(
            2,
            ProjectRequest.of(sut)
        )

        // then
        val expect = projectRepository.findById(0).get()
        assertEquals(expect.id, 0)
    }

    @Test
    fun 프로젝트를_포크한다() {
        // given
        val author = User("author", "")
        val forker = User("forker", "")
        val sut = Project(author, author, Tracks(emptyList()))

        userRepository.save(author)
        userRepository.save(forker)

        projectService.create(
            1,
            ProjectRequest.of(sut)
        )

        // when
        projectService.fork(
            2,
            1
        )

        // then
        val expect = projectRepository.findById(2).get()
        assertEquals(expect.owner.id, 2)
    }
}