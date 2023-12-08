package com.dygames.bandmates.domain.project

import com.dygames.bandmates.domain.BaseEntity
import jakarta.persistence.Entity

@Entity
class User(
    val name: String,
    val email: String
) : BaseEntity()
