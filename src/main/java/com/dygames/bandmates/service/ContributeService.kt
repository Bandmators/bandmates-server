package com.dygames.bandmates.service

import com.dygames.bandmates.domain.contribute.Contribute
import com.dygames.bandmates.domain.contribute.ContributeState
import com.dygames.bandmates.domain.repository.ContributeRepository
import com.dygames.bandmates.domain.repository.MemberRepository
import com.dygames.bandmates.domain.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ContributeRequest
import com.dygames.bandmates.service.dto.ContributeResponse
import com.dygames.bandmates.service.dto.ContributesResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class ContributeService(
    private val contributeRepository: ContributeRepository,
    private val projectRepository: ProjectRepository,
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun findById(projectId: Long, contributeId: Long): ContributeResponse {
        val contribute = contributeRepository.findById(contributeId).get()

        return ContributeResponse.of(contribute)
    }

    @Transactional
    fun findAllRequest(request: ContributeRequest): ContributesResponse {
        val member = memberRepository.findById(request.memberId).get()

        return ContributesResponse.of(
            contributeRepository.findAllByForkedOwner(member)
        )
    }

    @Transactional
    fun findAllRequested(request: ContributeRequest): ContributesResponse {
        val member = memberRepository.findById(request.memberId).get()

        return ContributesResponse.of(
            contributeRepository.findAllByOriginOwner(member)
        )
    }

    @Transactional
    fun request(memberId: Long, projectId: Long): ContributeResponse {
        val project = projectRepository.findById(projectId).get()
        val originProject = projectRepository.findById(project.originId).get()

        val contribute = Contribute(
            originProject, project, ContributeState.OPEN
        )

        return ContributeResponse.of(
            contributeRepository.save(contribute)
        )
    }

    @Transactional
    fun cancel(memberId: Long, contributeId: Long) {
        contributeRepository.deleteById(contributeId)
    }

    @Transactional
    fun accept(memberId: Long, contributeId: Long): ContributeResponse {
        val contribute = contributeRepository.findById(contributeId).get()
        val originProject = contribute.origin
        val forkedProject = contribute.forked

        val mergedProject = originProject.merge(forkedProject)
        val acceptedContribute = contribute.accept()

        projectRepository.save(mergedProject)

        return ContributeResponse.of(
            contributeRepository.save(acceptedContribute)
        )
    }

    @Transactional
    fun decline(memberId: Long, contributeId: Long): ContributeResponse {
        val contribute = contributeRepository.findById(contributeId).get()

        val declinedContribute = contribute.decline()

        return ContributeResponse.of(
            contributeRepository.save(declinedContribute)
        )
    }
}