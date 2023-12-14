package com.dygames.bandmates.controller

import com.dygames.bandmates.service.ProjectService
import com.dygames.bandmates.service.dto.ProjectResponse
import com.dygames.bandmates.service.dto.ProjectsResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("projects")
class ProjectController(
    private val projectService: ProjectService
) {

    @GetMapping
    fun findAllByMemberId(
        @RequestBody memberId: Long,
    ): ResponseEntity<ProjectsResponse> {
        val projects = projectService.findAllByMemberId(memberId)
        return ResponseEntity.ok().body(projects)
    }

    @GetMapping("/{projectId}")
    fun findById(
        @PathVariable projectId: Long
    ): ResponseEntity<ProjectResponse> {
        val project = projectService.findById(projectId)
        return ResponseEntity.ok().body(project)
    }

    @PostMapping
    fun create(
        @RequestHeader(value = "Authorization") memberId: Long,
    ): ResponseEntity<Unit> {
        projectService.create(memberId)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/{projectId}")
    fun delete(
        @RequestHeader(value = "Authorization") memberId: Long,
        @PathVariable projectId: Long,
    ): ResponseEntity<Unit> {
        projectService.delete(memberId, projectId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/fork/{projectId}")
    fun fork(
        @RequestHeader(value = "Authorization") memberId: Long,
        @PathVariable projectId: Long
    ): ResponseEntity<Unit> {
        projectService.fork(memberId, projectId)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
