package com.dygames.bandmates.controller

import com.dygames.bandmates.service.FileService
import com.dygames.bandmates.service.TrackService
import com.dygames.bandmates.service.dto.TrackRequest
import com.dygames.bandmates.service.dto.WaveAudioRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("projects/{projectId}/tracks")
class TrackController(
    private val trackService: TrackService,
    private val fileService: FileService
) {

    @PostMapping
    fun createWithWaveAudio(
        @PathVariable projectId: Long,
        @RequestHeader(value = "Authorization") memberId: Long,
        @RequestBody request: WaveAudioRequest
    ): ResponseEntity<Unit> {
        val path = fileService.store(request.audioRequest)
        val trackRequest = TrackRequest(
            projectId, memberId, path
        )
        trackService.createWithWaveAudio(trackRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping
    fun createWithMIDIAudio(
        @PathVariable projectId: Long,
        @RequestHeader(value = "Authorization") memberId: Long,
        @RequestBody request: WaveAudioRequest
    ): ResponseEntity<Unit> {
        val path = fileService.store(request.audioRequest)
        val trackRequest = TrackRequest(
            projectId, memberId, path
        )
        trackService.createWithMIDIAudio(trackRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
