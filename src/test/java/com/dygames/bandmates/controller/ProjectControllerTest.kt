package com.dygames.bandmates.controller

import com.dygames.bandmates.domain.project.Project
import com.dygames.bandmates.domain.project.Tracks
import com.dygames.bandmates.domain.project.User
import com.dygames.bandmates.service.ProjectService
import com.dygames.bandmates.service.dto.ProjectRequest
import com.dygames.bandmates.service.dto.ProjectsResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ProjectController::class)
class ProjectControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var projectService: ProjectService

    @Test
    fun 모든_프로젝트를_가져온다() {
        val sut = Project(User("", ""), User("", ""), Tracks(emptyList()))

        given(projectService.findAll())
            .willReturn(
                ProjectsResponse.of(
                    listOf(sut)
                )
            )

        mvc.perform(get("/projects"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.value[0].id").value("0"))
            .andExpect(jsonPath("$.value[0].author").value(""))
    }

    @Test
    fun 프로젝트를_생성한다() {
        val sut = Project(User("", ""), User("", ""), Tracks(emptyList()))

        val body: String = ObjectMapper().writeValueAsString(
            ProjectRequest.of(sut)
        )

        mvc.perform(
            post("/projects")
                .header(HttpHeaders.AUTHORIZATION, "2")
                .content(body).contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
    }

    @Test
    fun 프로젝트를_포크한다() {
        val sut = Project(User("", ""), User("", ""), Tracks(emptyList()))

        val body: String = ObjectMapper().writeValueAsString(
            ProjectRequest.of(sut)
        )

        mvc.perform(
            post("/projects/fork/1")
                .header(HttpHeaders.AUTHORIZATION, "2")
                .content(body).contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
    }
}
