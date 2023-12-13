package com.dygames.bandmates.service

import com.dygames.bandmates.domain.contribute.Contribute
import com.dygames.bandmates.domain.contribute.ContributeState
import com.dygames.bandmates.domain.project.Member
import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.repository.ContributeRepository
import com.dygames.bandmates.domain.repository.MemberRepository
import com.dygames.bandmates.domain.repository.ProjectRepository
import org.junit.jupiter.api.Assertions.assertEquals
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

        val originProject = projectRepository.save(Project(author, author, Tracks(emptyList())))
        val forkedProject = projectRepository.save(
            Project(author, forker, Tracks(emptyList()), originProject.id)
        )

        // when
        val contribute = contributeService.request(
            forker.id,
            forkedProject.id
        )

        // then
        val expected = contributeRepository.findById(contribute.id).get()
        assertEquals(expected.origin.id, originProject.id)
    }

    @Test
    fun 기여_요청을_취소한다() {
        // given
        val author = memberRepository.save(Member("author", ""))
        val forker = memberRepository.save(Member("forker", ""))

        val originProject = projectRepository.save(Project(author, author, Tracks(emptyList())))
        val forkedProject = projectRepository.save(
            Project(author, forker, Tracks(emptyList()), originProject.id)
        )

        val contribute = contributeRepository.save(
            Contribute(
                originProject,
                forkedProject,
                ContributeState.OPEN
            )
        )

        // when
        contributeService.cancel(forker.id, contribute.id)

        // then
        val expected = contributeRepository.existsById(contribute.id)
        assertEquals(expected, false)
    }

    @Test
    fun 기여_요청을_수락한다() {
        // given
        val author = memberRepository.save(Member("author", ""))
        val forker = memberRepository.save(Member("forker", ""))

        val originProject = projectRepository.save(Project(author, author, Tracks(emptyList())))
        val forkedProject = projectRepository.save(
            Project(author, forker, Tracks(emptyList()), originProject.id)
        )

        val contribute = contributeRepository.save(
            Contribute(
                originProject,
                forkedProject,
                ContributeState.OPEN
            )
        )

        // when
        contributeService.accept(forker.id, contribute.id)

        // then
        val expected = contributeRepository.findById(contribute.id).get()
        assertEquals(expected.state, ContributeState.ACCEPTED)
    }

    @Test
    fun 기여_요청을_반려한다() {
        // given
        val author = memberRepository.save(Member("author", ""))
        val forker = memberRepository.save(Member("forker", ""))

        val originProject = projectRepository.save(Project(author, author, Tracks(emptyList())))
        val forkedProject = projectRepository.save(
            Project(author, forker, Tracks(emptyList()), originProject.id)
        )

        val contribute = contributeRepository.save(
            Contribute(
                originProject,
                forkedProject,
                ContributeState.OPEN
            )
        )

        // when
        contributeService.decline(forker.id, contribute.id)

        // then
        val expected = contributeRepository.findById(contribute.id).get()
        assertEquals(expected.state, ContributeState.DECLINED)
    }
}