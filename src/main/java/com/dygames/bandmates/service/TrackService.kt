package com.dygames.bandmates.service

import com.dygames.bandmates.domain.repository.MemberRepository
import com.dygames.bandmates.domain.repository.ProjectRepository
import com.dygames.bandmates.domain.track.Track
import com.dygames.bandmates.domain.track.audio.Audio
import com.dygames.bandmates.domain.track.audio.MIDIAudio
import com.dygames.bandmates.domain.track.audio.WaveAudio
import com.dygames.bandmates.service.dto.TrackRequest
import com.dygames.bandmates.service.dto.TrackResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TrackService(
    private val projectRepository: ProjectRepository,
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun createWithWaveAudio(trackRequest: TrackRequest): TrackResponse {
        return create(trackRequest, WaveAudio(trackRequest.path.toString()))
    }

    @Transactional
    fun createWithMIDIAudio(trackRequest: TrackRequest): TrackResponse {
        return create(trackRequest, MIDIAudio(trackRequest.path.toString()))
    }

    private fun create(trackRequest: TrackRequest, audio: Audio): TrackResponse {
        val project = projectRepository.findById(trackRequest.projectId).get()
        val author = memberRepository.findById(trackRequest.memberId).get()
        val track = Track(
            audio,
            author
        )
        val addedProject = project.addTrack(track)
        projectRepository.save(addedProject)
        return TrackResponse.of(
            track
        )
    }
}