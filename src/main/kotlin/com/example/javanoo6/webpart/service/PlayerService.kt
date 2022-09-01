package com.example.javanoo6.webpart.service

import com.example.javanoo6.webpart.core.impl.GameImpl
import com.example.javanoo6.webpart.core.impl.PingPongTableImpl
import com.example.javanoo6.webpart.core.impl.PlayerImpl
import com.example.javanoo6.webpart.exceptions.PLayerNotFoundException
import com.example.javanoo6.webpart.exceptions.PlayerSaveException
import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.repository.PlayerRepository
import com.example.javanoo6.webpart.request.PlayerRequest
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PlayerService(
    val playerRepository: PlayerRepository
//    val game : GameImpl
//    val playerRepository: PlayerRepository, val gameRepSer: GameRecordService
//    val playerRepository: PlayerRepository, val gameRepSer: GameRecordService
) {
    @Autowired
    lateinit var game: GameImpl

    //    val game = GameImpl(gameRepSer)
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
        val errorMessages = mutableListOf<String>()
        mutableListOf(firstPlayer, secondPlayer).forEach {
            when {
                it.isEmpty() -> {
                    errorMessages.add("$it  - Имя пустое")
                }
                playerRepository.findPlayerByName(it).isNotEmpty() -> {
                    errorMessages.add("Такой игрок с $it именем уже существует и в базу данных игроков сохранен не будет")

                }
                else -> {
                    playerRepository.save(
                        Player(
                            name = it, score = null
                        )
                    )
                }
            }
        }

        if (errorMessages.isNotEmpty()) throw PlayerSaveException("устраните вышеуказанные ошибки : $errorMessages")
        return ("Оба игрока ${request.playerOneName} ${request.playerTwoName} были добавлены в базу данных")


    }


    fun startGame(finalScore: Int): String {
        playerOne = PlayerImpl(
            pingPongTable.playerOneTablePoints, pingPongTable.playerOneTablePointsForShouting, firstPlayer

        )

        playerTwo = PlayerImpl(
            pingPongTable.playerTwoTablePoints, pingPongTable.playerTwoTablePointsForShouting, secondPlayer
        )
        checkInitializedPlayers(firstPlayer, secondPlayer, finalScore)

        game.run(playerOne, playerTwo, finalScore)
        return "Игра была успешно запущена"

    }

    fun checkInitializedPlayers(firstPlayer: String, secondPlayer: String, finalScore: Int) {
        if (firstPlayer.isEmpty()) throw Exception("Имя первого игрока пустое")
        if (secondPlayer.isEmpty()) throw Exception("Имя второго игрока пустое")
        if (finalScore == 0) throw Exception("Количество поинтов не может быть равно 0")

    }

    fun findPlayerByName(name: String): List<Player> {
        return playerRepository.findPlayerByName(name)

    }
}
