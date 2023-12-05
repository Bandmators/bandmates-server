package com.dygames.bandmates.service.dto

import com.dygames.bandmates.domain.project.Project

data class ProjectResponse(
    val id: Long,
    val author: String,
    val tracks: TracksResponse
) {
    companion object {
        fun of(project: Project) =ProjectResponse(
            project.id,
            project.author.name,
            TracksResponse.of(project.tracks)
        )
    }
}