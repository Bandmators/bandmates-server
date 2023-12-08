package com.dygames.bandmates.domain

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.repository.ProjectRepository

class FakeProjectRepository : FakeRepository<Project>(), ProjectRepository