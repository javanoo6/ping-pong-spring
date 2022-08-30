package com.example.javanoo6.remake.core.impl

import com.example.javanoo6.remake.core.Game
import com.example.javanoo6.webpart.service.GameRecordService
import org.springframework.stereotype.Component

@Component
class GameImpl(var gameRepa: GameRecordService) : Game {

//    @Autowired
//    lateinit var gameRepa: GameRecordService

    lateinit var theWinner: PlayerImpl
    var isGameOver = false

    //    override fun run(playerOne: PlayerImpl, playerTwo: PlayerImpl, finalScore: Int, gameRepa: GameRecordService?) {
    override fun run(playerOne: PlayerImpl, playerTwo: PlayerImpl, finalScore: Int) {
        println("ИГРА ПИНГ-ПОНГ")
//        playerOne.score = 0
//        playerTwo.score = 0
        isGameOver = false
        println(finalScore)
        while (playerOne.score < finalScore || playerTwo.score < finalScore) {
            playerMove(playerOne, playerTwo, finalScore)
            if (isGameOver) break
            playerMove(playerTwo, playerOne, finalScore)
            if (isGameOver) break
        }
        getWinner(playerOne, playerTwo)
//        getWinner(playerOne, playerTwo, gameRep)

    }

    private fun playerMove(
        playerFirst: PlayerImpl, playerSecond: PlayerImpl, MAXIMUM_GAME_POINT: Int

    ) {

        val hit = playerFirst.hit()
        println("\nсейчас ходит ${playerFirst.name} и попадает по $hit")
        if (playerSecond.playerTablePoints.contains(hit)) {
            println("${playerFirst.name} попадает по столу соперника -> игра продолжается")
        } else {
            println("${playerFirst.name} промахнулся, ${playerSecond.name} получает + 1 балл")
            playerSecond.score++
            getOverAllGameInfo(playerFirst, playerSecond, MAXIMUM_GAME_POINT)
        }
    }

    private fun getOverAllGameInfo(
        playerFirst: PlayerImpl, playerSecond: PlayerImpl, MAXIMUM_GAME_POINT: Int
    ) {
        println(
            "предварительный счет:" + "\nсчет участника: ${playerFirst.name} - ${playerFirst.score}" + "\nсчет участника: ${playerSecond.name} - ${playerSecond.score}"
        )
        if (playerFirst.score == MAXIMUM_GAME_POINT || playerSecond.score == MAXIMUM_GAME_POINT) {
            isGameOver = true

        }
    }


    fun getWinner(playerOne: PlayerImpl, playerTwo: PlayerImpl) {
//    fun getWinner(playerOne: PlayerImpl, playerTwo: PlayerImpl, gameRep: GameRecordService) {
        if (playerOne.score > playerTwo.score) {
            println("\n${playerOne.name} победил")
            theWinner = playerOne

        } else {
            println("\n${playerTwo.name} победил")
            theWinner = playerTwo

        }
        gameRepa.saveGame(playerOne, playerTwo, theWinner)
//        gameRep.saveGame(playerOne, playerTwo, theWinner)

    }


}

