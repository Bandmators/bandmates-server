package com.dygames.bandmates.service

import com.dygames.bandmates.domain.contribute.Contribute
import com.dygames.bandmates.domain.contribute.ContributeState
import com.dygames.bandmates.domain.contribute.repository.ContributeRepository
import com.dygames.bandmates.domain.project.repository.MemberRepository
import com.dygames.bandmates.domain.project.repository.ProjectRepository
import com.dygames.bandmates.service.dto.ContributeResponse
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
    fun request(memberId: Long, projectId: Long): ContributeResponse {
        val requester = memberRepository.findById(memberId).get()
        val project = projectRepository.findById(projectId).get()
        val originProject = projectRepository.findById(project.originId).get()

        val contribute = Contribute(
            requester,
            originProject,
            project,
            ContributeState.OPEN
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