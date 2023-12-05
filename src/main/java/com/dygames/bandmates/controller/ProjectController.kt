package com.dygames.bandmates.controller

import com.dygames.bandmates.service.ProjectService
import com.dygames.bandmates.service.dto.ProjectRequest
import com.dygames.bandmates.service.dto.ProjectsResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
class ProjectController(
    private val projectService: ProjectService
) {

    @PostMapping
    fun create(@RequestBody request: ProjectRequest): ResponseEntity<Unit> {
        projectService.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<ProjectsResponse> {
        return ResponseEntity.ok().body(projectService.findAll())
    }
}
