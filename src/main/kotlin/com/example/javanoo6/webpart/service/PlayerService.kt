package com.example.javanoo6.webpart.service

import com.example.javanoo6.remake.core.impl.GameImpl
import com.example.javanoo6.remake.core.impl.PingPongTableImpl
import com.example.javanoo6.remake.core.impl.PlayerImpl
import com.example.javanoo6.webpart.exceptions.PLayerNotFoundException
import com.example.javanoo6.webpart.exceptions.PlayerSaveException
import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.repository.PlayerRepository
import com.example.javanoo6.webpart.request.PlayerRequest
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PlayerService {
    @Autowired
    lateinit var playerRepository: PlayerRepository

    @Autowired
    lateinit var gameRepSer: GameRecordService
    val game = GameImpl(gameRepSer)
    val pingPongTable = PingPongTableImpl()
    lateinit var firstPlayer: String
    lateinit var secondPlayer: String
    lateinit var playerOne: PlayerImpl
    lateinit var playerTwo: PlayerImpl


    fun findById(id: ObjectId): Player =
        playerRepository.findById(id).orElseThrow { (PLayerNotFoundException("такого игрока с $id не найдено ")) }

    @Throws(PlayerSaveException::class)
    fun setPlayersNames(request: PlayerRequest): String {

        firstPlayer = request.playerOneName
        secondPlayer = request.playerTwoName

        mutableListOf(firstPlayer, secondPlayer).forEach {
            when {
                it.isEmpty() -> {
                    throw Exception("Имя пустое")
                }
                playerRepository.findPlayerByName(it).isNotEmpty() -> {

                    throw  PlayerSaveException("Такой игрок с $it именем уже существует ")
                }
                else -> {
                    playerRepository.save(
                        Player(
                            name = it,
                            score = null
                        )
                    )
                }
            }
        }

        return ("Оба игрока ${request.playerOneName} ${request.playerTwoName} были добавлены в базу данных")

    }

    fun startGame(finalScore: Int): String {
        playerOne = PlayerImpl(
            pingPongTable.playerOneTablePoints,
            pingPongTable.playerOneTablePointsForShouting,
            firstPlayer

        )

        playerTwo = PlayerImpl(
            pingPongTable.playerTwoTablePoints,
            pingPongTable.playerTwoTablePointsForShouting,
            secondPlayer
        )
        checkInitializedPlayers(firstPlayer, secondPlayer)
        game.run(playerOne, playerTwo, finalScore)
//        game.run(playerOne, playerTwo, finalScore, gameRepSer)
        return "Игра была успешно запущена"

    }

    private fun checkInitializedPlayers(firstPlayer: String, secondPlayer: String) {
        if (firstPlayer.isEmpty()) throw Exception("Имя первого игрока пустое")
        if (secondPlayer.isEmpty()) throw Exception("Имя второго игрока пустое")

    }

    fun findPlayerByName(name: String): List<Player> {
        return playerRepository.findPlayerByName(name)

    }
}
