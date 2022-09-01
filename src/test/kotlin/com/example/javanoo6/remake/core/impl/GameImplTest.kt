package com.example.javanoo6.remake.core.impl

import com.example.javanoo6.webpart.core.impl.GameImpl
import com.example.javanoo6.webpart.core.impl.PingPongTableImpl
import com.example.javanoo6.webpart.core.impl.PlayerImpl
import com.example.javanoo6.webpart.service.GameRecordService
import io.kotest.matchers.should
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class GameImplTest {

    private val gameRepSer = mockk<GameRecordService>()
    private val game: GameImpl = GameImpl()

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


    init {
        every {
            gameRepSer.saveGame(playerOne, playerTwo, any<PlayerImpl>())
        } returns Unit
    }


    @Test
    fun run() {
        game.run(playerOne, playerTwo, finalScore = 10).should { game.isGameOver }
    }
}