package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.project.Project

data class ProjectsResponse(
    val value: List<ProjectResponse>
) {
    companion object {
        fun of(projects: List<Project>) = projects.map {
            ProjectResponse.of(it)
        }.let {
            ProjectsResponse(it)
        }
    }
}
