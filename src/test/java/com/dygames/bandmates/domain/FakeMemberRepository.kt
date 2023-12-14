package com.dygames.bandmates.domain

import com.dygames.bandmates.domain.member.Member
import com.dygames.bandmates.domain.repository.MemberRepository

class FakeMemberRepository : FakeRepository<Member>(), MemberRepository