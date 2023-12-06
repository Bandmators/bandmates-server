package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.project.Project

data class ProjectRequest(
    val id: Long,
    val author: UserRequest
) {
    companion object {
        fun of(project: Project) = ProjectRequest(
            project.id,
            UserRequest.of(project.author)
        )
    }
}