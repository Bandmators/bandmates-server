package com.dygames.bandmates.service.dto

import org.springframework.web.multipart.MultipartFile

data class AudioRequest(
    val file: MultipartFile
)