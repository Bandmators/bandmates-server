package com.dygames.bandmates.domain.project

import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    val id: Long = 0,
    val name: String = "",
    val email: String = ""
)
