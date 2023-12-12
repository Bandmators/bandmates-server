package com.dygames.bandmates.service

import com.dygames.bandmates.domain.contribute.repository.ContributeRepository
import com.dygames.bandmates.domain.project.Member
import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.project.repository.MemberRepository
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ContributeServiceTest {

    @Autowired
    private lateinit var contributeRepository: ContributeRepository

    @Autowired
    private lateinit var projectRepository: ProjectRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository

    private val contributeService: ContributeService by lazy {
        ContributeService(contributeRepository, projectRepository, memberRepository)
    }

    @Test
    fun 프로젝트에_기여_요청을_보낸다() {
        // given
        val author = memberRepository.save(Member("author", ""))
        val forker = memberRepository.save(Member("forker", ""))

        val createdProject = projectRepository.save(Project(author, author, Tracks(emptyList())))
        val forkedProject = projectRepository.save(
            Project(author, forker, Tracks(emptyList()), createdProject.id)
        )

        // when
        val contributedProject = contributeService.request(
            forker.id,
            forkedProject.id
        )

        // then
        val expect = projectRepository.findById(contributedProject.id).get()
        Assertions.assertEquals(expect.id, createdProject.id)
    }
}