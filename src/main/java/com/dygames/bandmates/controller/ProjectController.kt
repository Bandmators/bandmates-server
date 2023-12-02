package com.dygames.bandmates.controller

import com.dygames.bandmates.service.ProjectService
import com.dygames.bandmates.service.dto.ProjectsResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
class ProjectController(
    val projectService: ProjectService
) {
    @GetMapping
    fun findProjects(): ProjectsResponse {
        return ProjectsResponse(0)
    }
}
