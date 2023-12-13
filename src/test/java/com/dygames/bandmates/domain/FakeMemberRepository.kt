package com.dygames.bandmates.domain

import com.dygames.bandmates.domain.project.Member
import com.dygames.bandmates.domain.repository.MemberRepository

class FakeMemberRepository : FakeRepository<Member>(), MemberRepository