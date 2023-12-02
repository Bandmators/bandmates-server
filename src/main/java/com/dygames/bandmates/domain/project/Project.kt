package com.dygames.bandmates.domain.project

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID")
    val id: Long = 0,

    @OneToOne
    @JoinColumn(name = "USER_ID")
    val author: User = User(),

    @Embedded
    val tracks: Tracks = Tracks()
)

