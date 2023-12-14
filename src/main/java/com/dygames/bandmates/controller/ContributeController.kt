package com.dygames.bandmates.controller

import com.dygames.bandmates.service.ContributeService
import com.dygames.bandmates.service.dto.ContributeRequest
import com.dygames.bandmates.service.dto.ContributeResponse
import com.dygames.bandmates.service.dto.ContributesResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/projects/{projectId}/contributes")
class ContributeController(
    private val contributeService: ContributeService
) {

    @GetMapping("/{contributeId}")
    fun findById(
        @PathVariable projectId: Long,
        @PathVariable contributeId: Long
    ): ResponseEntity<ContributeResponse> {
        val contribute = contributeService.findById(projectId, contributeId)
        return ResponseEntity.ok().body(contribute)
    }

    @GetMapping("/request")
    fun findAllRequest(
        @RequestBody request: ContributeRequest
    ): ResponseEntity<ContributesResponse> {
        val contributes = contributeService.findAllRequest(request)
        return ResponseEntity.ok().body(contributes)
    }

    @GetMapping("/requested")
    fun findAllRequested(
        @RequestBody request: ContributeRequest
    ): ResponseEntity<ContributesResponse> {
        val contributes = contributeService.findAllRequested(request)
        return ResponseEntity.ok().body(contributes)
    }

    @PostMapping
    fun request(
        @RequestHeader(value = "Authorization") memberId: Long,
        @PathVariable projectId: Long
    ): ResponseEntity<ContributeResponse> {
        val contribute = contributeService.request(memberId, projectId)
        return ResponseEntity.status(HttpStatus.CREATED).body(contribute)
    }

    @DeleteMapping("/{contributeId}")
    fun cancel(
        @RequestHeader(value = "Authorization") memberId: Long,
        @PathVariable projectId: Long,
        @PathVariable contributeId: Long
    ): ResponseEntity<Unit> {
        contributeService.cancel(memberId, contributeId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PatchMapping("/{contributeId}/accept")
    fun accept(
        @RequestHeader(value = "Authorization") memberId: Long,
        @PathVariable projectId: Long,
        @PathVariable contributeId: Long
    ): ResponseEntity<ContributeResponse> {
        val contribute = contributeService.accept(memberId, contributeId)
        return ResponseEntity.ok().body(contribute)
    }

    @PatchMapping("/{contributeId}/decline")
    fun decline(
        @RequestHeader(value = "Authorization") memberId: Long,
        @PathVariable projectId: Long,
        @PathVariable contributeId: Long
    ): ResponseEntity<ContributeResponse> {
        val contribute = contributeService.decline(memberId, contributeId)
        return ResponseEntity.ok().body(contribute)
    }
}