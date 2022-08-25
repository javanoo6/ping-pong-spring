package com.example.javanoo6.remake.core.impl

import com.example.javanoo6.webpart.service.GameRecordService
import io.kotest.matchers.should
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class GameImplTest {

    //    companion object {
    val game: GameImpl
    val pingPongTable: PingPongTableImpl
    val playerOne: PlayerImpl
    val playerTwo: PlayerImpl
    val gameRep = mockk<GameRecordService>()

    //    }
    init {
        pingPongTable = PingPongTableImpl()
        game = GameImpl(gameRep)
//        gameRep= GameRecordService()

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
            gameRep.saveGame(playerOne, playerTwo, any<PlayerImpl>())
        } returns Unit
    }


    @Test
    fun run() {
        game.run(playerOne, playerTwo, finalScore = 10).should { game.isGameOver }
    }
}