package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.request.PlayerRequest
import com.example.javanoo6.webpart.service.PlayerService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.mockk
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
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
    val finalScore = 10
    val objectId: ObjectId = ObjectId.get()
    var notCorrectPlayerRequest = PlayerRequest("", " ")
    var correctPlayerRequest = PlayerRequest("nameOne", "pl2")
    var objectMapper = ObjectMapper()

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }


    @Test
    fun `should find player by id`() {
        mockMvc.get("$baseUrl/id/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(objectId)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { HttpStatus.OK }
        }.andExpect {
            MockMvcResultMatchers.content().string("{}")
        }
    }


    @Test
    fun `should find player by name`() {
        mockMvc.get("$baseUrl/name/:name") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(name)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { HttpStatus.OK }
        }.andExpect {
            MockMvcResultMatchers.content().string("{}")
        }
    }

    @Test
    fun `should  setup player names`() {
        mockMvc.post("$baseUrl/playerNames") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(correctPlayerRequest)
            accept = MediaType.APPLICATION_JSON

        }.andExpect {
            status { HttpStatus.CREATED }

        }.andExpect {
            MockMvcResultMatchers.content().string("{}")
        }
    }


    @Test
    fun `should not setup player names`() {
        mockMvc.post("$baseUrl/playerNames") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(notCorrectPlayerRequest)
            accept = MediaType.APPLICATION_JSON

        }.andExpect {
            status { HttpStatus.CONFLICT }
        }
    }

    @Test
    fun `should start game`() {
        mockMvc.get("$baseUrl/start") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(finalScore)
            accept = MediaType.APPLICATION_JSON
        }.andExpect { status { HttpStatus.ACCEPTED } }

    }


}


