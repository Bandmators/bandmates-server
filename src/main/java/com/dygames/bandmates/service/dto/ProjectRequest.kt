package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.project.Project

data class ProjectRequest(
    val id: Long,
    val author: MemberRequest
) {
    companion object {
        fun of(project: Project) = ProjectRequest(
            project.id,
            MemberRequest.of(project.author)
        )
    }
}