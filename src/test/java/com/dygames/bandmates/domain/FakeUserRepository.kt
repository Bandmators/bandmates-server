package com.dygames.bandmates.domain

import com.dygames.bandmates.domain.project.User
import com.dygames.bandmates.domain.project.repository.UserRepository

class FakeUserRepository : FakeRepository<User>(), UserRepository