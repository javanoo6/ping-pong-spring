package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.request.PlayerRequest
import com.example.javanoo6.webpart.service.PlayerService
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.AnnotationSpec.Before
import io.mockk.mockk
import org.bson.types.ObjectId
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest
internal class PlayerControllerTest {

    val baseUrl = "/players"
    val playerService = mockk<PlayerService>()
    val name = "PlayerName"
    val objectId = mockk<ObjectId>()
    var request = mockk<PlayerRequest>()

    private var webApplicationContext: WebApplicationContext? = null
    private var mockMvc: MockMvc? = null
    private var objectMapper: ObjectMapper? = null

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext!!).build()

    }

    @Nested
    @DisplayName("GET /id{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindPlayerById {
        @Test
        fun `should find player by id`() {
            mockMvc?.get("$baseUrl/id{$objectId}")?.andExpect { status { isOk() } }
                ?.andExpect { MockMvcResultMatchers.jsonPath("$.id").value("playerName") }
        }
    }

    @Nested
    @DisplayName("GET /name/{name}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindPlayerByName {

        @Test
        fun `should find player by name`() {
            mockMvc?.get("$baseUrl/name/{$name}")?.andExpect { status { isOk() } }
                ?.andExpect { MockMvcResultMatchers.jsonPath("$.name").value("playerName") }
        }
    }

    @Nested
    @DisplayName("POST /playerNames")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class SetPlayerNamesService {
        @Test
        fun `should setup player names`() {
            val performPost = mockMvc?.post("$baseUrl/$request") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper?.writeValueAsString(request)
            }

            performPost?.andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    objectMapper?.writeValueAsString(request)?.let { json(it) }
                }
            }
        }
    }

    @Nested
    @DisplayName("GET /start")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GameStart {

        @Test
        fun `should start game`() {
            mockMvc?.get("$baseUrl/start")?.andExpect { status { isOk() } }

        }

    }

}
