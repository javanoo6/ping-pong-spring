package com.example.javanoo6.webpart.service

import com.example.javanoo6.webpart.core.impl.GameImpl
import com.example.javanoo6.webpart.core.impl.PingPongTableImpl
import com.example.javanoo6.webpart.core.impl.PlayerImpl
import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.repository.PlayerRepository
import io.kotest.matchers.should
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

internal class PlayerServiceTest {

    val gameRepSer = mockk<GameRecordService>()
    val playerRepository = mockk<PlayerRepository>()
    val player = mockk<Player>()
    val objectId = mockk<ObjectId>()
    val game: GameImpl
    val pingPongTable: PingPongTableImpl
    val playerOne: PlayerImpl
    val playerTwo: PlayerImpl
    val name = "PlayeOne"

    init {
        game = GameImpl()
//        game = GameImpl(gameRepSer)
        pingPongTable = PingPongTableImpl()

        playerOne = PlayerImpl(
            pingPongTable.playerOneTablePoints,
            pingPongTable.playerOneTablePointsForShouting,
            "игрокНомерОдин", 0
        )
        playerTwo = PlayerImpl(
            pingPongTable.playerTwoTablePoints,
            pingPongTable.playerTwoTablePointsForShouting,
            "ИгрокНомерДва", 0
        )
        every {
            gameRepSer.saveGame(playerOne, playerTwo, any<PlayerImpl>())
        } returns Unit

        every {
            playerRepository.findPlayerByName(name)
        } returns mutableListOf()
    }


    @Test
    fun findById() {
        every { playerRepository.findByIdOrNull(objectId) } returns player
    }

    @Test
    fun startGame() {
        game.run(playerOne, playerTwo, 5).should { game.isGameOver }
    }

    @Test
    fun findPlayerByName() {
        val result = playerRepository.findPlayerByName(name)
        verify { playerRepository.findPlayerByName(name) }
        assertEquals(listOf<Player>(), result)
    }


}


