package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.project.Project

data class ProjectsResponse(
    val id: Long,

    ) {
    companion object {
        fun of(projects: List<Project>) = projects.map {

        }
    }
}
