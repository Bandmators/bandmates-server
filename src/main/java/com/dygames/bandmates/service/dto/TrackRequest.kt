package com.dygames.bandmates.service.dto

import java.nio.file.Path

data class TrackRequest(
    val projectId: Long,
    val memberId: Long,
    val path: Path
)