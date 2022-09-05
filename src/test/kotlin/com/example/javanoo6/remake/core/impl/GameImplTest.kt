package com.example.javanoo6.remake.core.impl

import com.example.javanoo6.webpart.core.impl.GameImpl
import com.example.javanoo6.webpart.core.impl.PingPongTableImpl
import com.example.javanoo6.webpart.core.impl.PlayerImpl
import com.example.javanoo6.webpart.model.GameRecord
import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.service.GameRecordService
import io.kotest.matchers.should
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class GameImplTest {

    private val gameRecordService = mockk<GameRecordService>()
    private val game: GameImpl = GameImpl(gameRecordService)

    //    private val game: GameImpl =GameImpl(gameRepSer)
    private val pingPongTable: PingPongTableImpl = PingPongTableImpl()
    private val playerOne = PlayerImpl(
        pingPongTable.playerOneTablePoints,
        pingPongTable.playerOneTablePointsForShouting,
        "игрокНомерОдин", 0
    )
    private val playerTwo = PlayerImpl(
        pingPongTable.playerTwoTablePoints,
        pingPongTable.playerTwoTablePointsForShouting,
        "ИгрокНомерДва", 0
    )
    var theWinner = playerTwo

    val gameRecord = GameRecord(
        firstParticipant = Player("${playerOne.name}", playerOne.score),
        secondParticipant = Player("${playerTwo.name}", playerTwo.score),
        theWinner = Player("${theWinner.name}", theWinner.score)
    )
//    init {
//        every {
//            gameRepSer.saveGame(playerOne, playerTwo, any<PlayerImpl>())
//        } returns Unit
//    }


    @Test
    fun run() {
        every {
            gameRecordService.saveGame(playerOne, playerTwo, playerTwo)
        } returns gameRecord
        val result = game.run(playerOne, playerTwo, finalScore = 10)
        verify { gameRecordService.saveGame(playerOne, playerTwo, playerTwo) }
//        game.run(playerOne, playerTwo, finalScore = 10).should { game.isGameOver }
    }
}