package com.example.javanoo6.webpart.controller

import io.mockk.mockk
import org.bson.types.ObjectId
import org.junit.Before
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

internal class GameControllerTest {

    val baseUrl = "/game"
    val objectId = mockk<ObjectId>()
    val name = "PlayerName"

    private var webApplicationContext: WebApplicationContext? = null
    private var mockMvc: MockMvc? = null

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext!!).build()
    }

    @Nested
    @DisplayName("GET /id{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindGameById {
        @Test
        fun `should find game by Id`() {
            mockMvc?.get("$baseUrl/id/$objectId")?.andExpect { status { isOk() } }
                ?.andExpect { MockMvcResultMatchers.jsonPath("$.objectId").value("not full game") }
        }
    }

    @Nested
    @DisplayName("GET /name")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindGameByName {
        @Test
        fun `should find game by playerName`() {
            mockMvc?.get("$baseUrl/$name")?.andExpect { status { isOk() } }
                ?.andExpect { MockMvcResultMatchers.jsonPath("$.name").value("game") }
        }


    }

    @Nested
    @DisplayName("GET /findById")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindFullGameById {
        @Test
        fun `should find full game by Id`() {
            mockMvc?.get("$baseUrl/findById")?.andExpect { status { isOk() } }
                ?.andExpect { MockMvcResultMatchers.jsonPath("$.findById").value("full game") }
        }

    }
}
