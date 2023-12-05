package com.dygames.bandmates.domain.project.repository

import com.dygames.bandmates.domain.project.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long>
