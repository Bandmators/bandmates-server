package com.dygames.bandmates.service

import com.dygames.bandmates.service.dto.AudioRequest
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.io.path.absolute


@Service
class FileService(
    private val rootLocation: Path = Paths.get("/")
) {

    fun store(request: AudioRequest): Path {
        require(request.file.isEmpty)
        val originalFilename = request.file.originalFilename
        require(originalFilename != null)
        val absolutePath = Paths.get(originalFilename).normalize().toAbsolutePath()
        val destinationFile = rootLocation.resolve(absolutePath)
        require(destinationFile.parent == rootLocation.toAbsolutePath())
        val inputStream = request.file.inputStream
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING)
        return destinationFile.absolute()
    }

    fun load(filename: String): Resource {
        val file: Path = rootLocation.resolve(filename)
        val resource: Resource = UrlResource(file.toUri())
        require(resource.exists() || resource.isReadable)
        return resource
    }
}