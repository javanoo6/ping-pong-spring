package com.example.javanoo6.webpart.controller

import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
internal class GameControllerTest {

    val baseUrl = "/game"
    val objectId: ObjectId = ObjectId.get()
    val objectIdString = ObjectId.get().toString()
    val name = "playerName"

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private val mockMvc: MockMvc by lazy {
        MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }
    var objectMapper = ObjectMapper()


    @Test
    fun `should find game by Id`() {
        mockMvc.get("$baseUrl/id/:id") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(objectIdString)
            accept = MediaType.APPLICATION_JSON
        }.andExpect { status { HttpStatus.OK } }
    }


    @Test
    fun `should find game by playerName`() {
        mockMvc.get("$baseUrl/name?name=") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(name)
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect { status { HttpStatus.OK } }
    }


    @Test
    fun `should find full game by Id`() {
        mockMvc.get("$baseUrl/findById") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(objectId)
            accept = MediaType.APPLICATION_JSON
        }.andExpect { status { HttpStatus.OK } }
    }

}

