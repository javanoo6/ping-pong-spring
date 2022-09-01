package com.example.javanoo6.webpart.service

import com.example.javanoo6.webpart.model.GameRecord
import com.example.javanoo6.webpart.repository.GameRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.aggregation.TypedAggregation
import java.util.*

internal class GameRecordServiceTest {

    var template = mockk<MongoTemplate>()

    val aggregation = mockk<TypedAggregation<Any>>()
    val gameRecord = mockk<GameRecord>()
    val output = mockk<AggregationResults<GameRecord>>()
    val gameRepository = mockk<GameRepository>()
    val gameRecordAsResponse = mockk<Optional<GameRecord>>()

    init {
        every { template.aggregate(aggregation, gameRecord::class.java) } returns output
        every { gameRepository.findById("someId") } returns gameRecordAsResponse
        every { gameRepository.save(gameRecord) } returns gameRecord
    }

    @Test
    fun findWinnerAndDateByName() {
        val result = template.aggregate(aggregation, gameRecord::class.java)

        println(result)
        verify { template.aggregate(aggregation, gameRecord::class.java) }
        assertEquals(output, result)

    }

    @Test
    fun findGameById() {
        val result = template.aggregate(aggregation, gameRecord::class.java)
        verify { template.aggregate(aggregation, gameRecord::class.java) }
        assertEquals(output, result)

    }

    @Test
    fun saveGame() {
        val result = gameRepository.save(gameRecord)
        verify { gameRepository.save(gameRecord) }
        assertEquals(gameRecord, result)
    }

    @Test
    fun findById() {
        val result = gameRepository.findById("someId")
        verify { gameRepository.findById("someId") }
        assertEquals(gameRecordAsResponse, result)

    }
}