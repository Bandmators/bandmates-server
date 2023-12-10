package com.dygames.bandmates.domain

import com.dygames.bandmates.domain.project.Member
import com.dygames.bandmates.domain.project.repository.MemberRepository

class FakeMemberRepository : FakeRepository<Member>(), MemberRepository