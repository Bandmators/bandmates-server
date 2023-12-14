package com.dygames.bandmates.controller

import com.dygames.bandmates.domain.contribute.Contribute
import com.dygames.bandmates.domain.contribute.ContributeState
import com.dygames.bandmates.domain.member.Member
import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.track.Tracks
import com.dygames.bandmates.service.ContributeService
import com.dygames.bandmates.service.dto.ContributeResponse
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ContributeController::class)
class ContributeControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var contributeService: ContributeService

    @Test
    fun 특정_기여를_가져온다() {
        val origin = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 1)
        val forked = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 2)

        val sut = Contribute(
            origin, forked, ContributeState.OPEN, 1
        )

        given(contributeService.findById(1, 1)).willReturn(
            ContributeResponse.of(sut)
        )

        mvc.perform(get("/projects/1/contributes/1")).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.author").value("")).andExpect(jsonPath("$.origin.id").value("1"))
            .andExpect(jsonPath("$.forked.id").value("2"))
    }

    @Test
    fun 기여를_요청한다() {
        val origin = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 1)
        val forked = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 2)

        val sut = Contribute(
            origin, forked, ContributeState.OPEN, 1
        )

        given(contributeService.request(1, 1)).willReturn(
            ContributeResponse.of(sut)
        )

        mvc.perform(
            post("/projects/1/contributes").header(HttpHeaders.AUTHORIZATION, "1")
        ).andExpect(status().isCreated).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.author").value(""))
            .andExpect(jsonPath("$.origin.id").value("1")).andExpect(jsonPath("$.forked.id").value("2"))
    }

    @Test
    fun 기여를_취소한다() {
        val origin = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 1)
        val forked = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 2)

        val sut = Contribute(
            origin, forked, ContributeState.OPEN, 1
        )

        given(contributeService.findById(1, 1)).willReturn(
            ContributeResponse.of(sut)
        )

        mvc.perform(
            delete("/projects/1/contributes/1").header(HttpHeaders.AUTHORIZATION, "1")
        ).andExpect(status().isNoContent)
    }

    @Test
    fun 기여를_수락한다() {
        val origin = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 1)
        val forked = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 2)

        val sut = Contribute(
            origin, forked, ContributeState.OPEN, 1
        )


        given(contributeService.accept(1, 1)).willReturn(
            ContributeResponse.of(sut)
        )

        mvc.perform(
            patch("/projects/1/contributes/1/accept").header(HttpHeaders.AUTHORIZATION, "1")
        ).andExpect(status().isOk).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.author").value(""))
            .andExpect(jsonPath("$.origin.id").value("1"))
            .andExpect(jsonPath("$.forked.id").value("2"))
    }

    @Test
    fun 기여를_거절한다() {
        val origin = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 1)
        val forked = Project(Member("", ""), Member("", ""), Tracks(emptyList()), 1, 2)

        val sut = Contribute(
            origin, forked, ContributeState.OPEN, 1
        )
        given(contributeService.decline(1, 1)).willReturn(
            ContributeResponse.of(sut)
        )

        mvc.perform(
            patch("/projects/1/contributes/1/decline").header(HttpHeaders.AUTHORIZATION, "1")
        ).andExpect(status().isOk).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.author").value(""))
            .andExpect(jsonPath("$.origin.id").value("1")).andExpect(jsonPath("$.forked.id").value("2"))
    }
}