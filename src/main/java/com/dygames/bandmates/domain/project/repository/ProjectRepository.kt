package com.dygames.bandmates.domain.project.repository

import com.dygames.bandmates.domain.project.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>
