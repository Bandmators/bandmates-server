package com.dygames.bandmates.service

import com.dygames.bandmates.domain.project.Member
import com.dygames.bandmates.domain.project.repository.MemberRepository
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ProjectServiceTest {

    @Autowired
    private lateinit var projectRepository: ProjectRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository

    private val projectService: ProjectService by lazy {
        ProjectService(projectRepository, memberRepository)
    }

    @Test
    fun 프로젝트를_생성한다() {
        // given
        val author = memberRepository.save(Member("", ""))

        // when
        val createdProject = projectService.create(
            author.id
        )

        // then
        val expect = projectRepository.findById(createdProject.id).get()
        assertEquals(expect.author.id, author.id)
    }

    @Test
    fun 프로젝트를_포크한다() {
        // given
        val author = memberRepository.save(Member("author", ""))
        val forker = memberRepository.save(Member("forker", ""))

        val createdProject = projectService.create(
            author.id
        )

        // when
        val forkedProject = projectService.fork(
            forker.id,
            createdProject.id
        )

        // then
        val expect = projectRepository.findById(forkedProject.id).get()
        assertEquals(expect.owner.id, forker.id)
    }

    @Test
    fun 프로젝트에_기여한다() {
        // given
        val author = memberRepository.save(Member("author", ""))
        val forker = memberRepository.save(Member("forker", ""))

        val createdProject = projectService.create(
            author.id
        )
        val forkedProject = projectService.fork(
            forker.id,
            createdProject.id
        )

        // when
        val contributedProject = projectService.contribute(
            forker.id,
            forkedProject.id
        )

        // then
        val expect = projectRepository.findById(contributedProject.id).get()
        assertEquals(expect.id, createdProject.id)
    }
}