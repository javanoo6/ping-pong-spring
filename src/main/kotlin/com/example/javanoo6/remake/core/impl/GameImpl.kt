package com.example.javanoo6.remake.core.impl

import com.example.javanoo6.remake.core.Game
import com.example.javanoo6.webpart.service.GameRecordService

//@PostConstruct
class GameImpl(
//    private var template: GameRecordService?
) : Game {
//    constructor() : this(template = null)
//
//var obj = object :GameRepository{
////    override fun <S : GameRecord?> save(entity: S): S {
////    }
//    constructor(gameRep: GameRepository, gameRep1: GameRepository){
//        this.gameRep=gameRep
//    this.gameRep = gameRep1
//}
//
//    constructor(gameRep: GameRepository?) {
//        if (gameRep != null) {
//            this.gameRep = gameRep
//        }
//    }
//
//    //    )
//    private lateinit var gameRep : GameRepository

    //    lateinit var gameRep: GameRepository
//    @Autowired
//    lateinit var gameRepository:GameRepository
//    val context = GenericApplicationContext().apply {
//
//    }
//   var template: GameRecordService

    var isGameOver = false

    override fun run(playerOne: PlayerImpl, playerTwo: PlayerImpl, finalScore: Int, gameRep: GameRecordService) {
        println("ИГРА ПИНГ-ПОНГ")
        playerOne.score = 0
        playerTwo.score = 0
        isGameOver = false
        println(finalScore)
        while (playerOne.score < finalScore || playerTwo.score < finalScore) {
            playerMove(playerOne, playerTwo, finalScore)
            if (isGameOver) break
            playerMove(playerTwo, playerOne, finalScore)
            if (isGameOver) break
        }
        getWinner(playerOne, playerTwo, gameRep)

    }

    private fun playerMove(
        playerFirst: PlayerImpl,
        playerSecond: PlayerImpl,
        MAXIMUM_GAME_POINT: Int

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
        playerFirst: PlayerImpl,
        playerSecond: PlayerImpl,
        MAXIMUM_GAME_POINT: Int
//        gameRep: GameRepository
    ) {
        println(
            "предварительный счет:" +
                    "\nсчет участника: ${playerFirst.name} - ${playerFirst.score}" +
                    "\nсчет участника: ${playerSecond.name} - ${playerSecond.score}"
        )
        if (playerFirst.score == MAXIMUM_GAME_POINT || playerSecond.score == MAXIMUM_GAME_POINT) {
            isGameOver = true

        }


    }

    lateinit var theWinner: PlayerImpl

    fun getWinner(playerOne: PlayerImpl, playerTwo: PlayerImpl, gameRep: GameRecordService) {
        if (playerOne.score > playerTwo.score) {
            println("\n${playerOne.name} победил")
            theWinner = playerOne
//            gameRep.gameRecord
//            var gameRep=object: GameRecordService(){

//            }
//            gameRep.save(
//                GameRecord(
//                    firstParticipant = Player(name = playerOne.name, score = playerOne.score),
//                    secondParticipant = Player(name = playerTwo.name, score = playerTwo.score),
//                    theWinner = Player(name = playerOne.name, score = playerOne.score)
//                )
//            )

        } else {
            println("\n${playerTwo.name} победил")
            theWinner = playerTwo
//
//
//            gameRep.save(
//                GameRecord(
//                    firstParticipant = Player(name = playerOne.name, score = playerOne.score),
//                    secondParticipant = Player(name = playerTwo.name, score = playerTwo.score),
//                    theWinner = Player(name = playerTwo.name, score = playerTwo.score)
//                )
//            )

        }
        gameRep.saveGame(playerOne,playerTwo,theWinner)
//            GameRecord(
//                firstParticipant = Player(name = playerOne.name, score = playerOne.score),
//                secondParticipant = Player(name = playerTwo.name, score = playerTwo.score),
//                theWinner = Player(name = theWinner.name, score = theWinner.score)
//            )
//        )
//        gameRep.save(
//            GameRecord(
//                firstParticipant = Player(name = playerOne.name, score = playerOne.score),
//                secondParticipant = Player(name = playerTwo.name, score = playerTwo.score),
//                theWinner = Player(name = theWinner.name, score = theWinner.score)
//            )
//        )

//        println(playerOne.score)
//        println(playerTwo.score)
//
//        playerOne.score = 0
//        playerTwo.score = 0
    }

//        println(
//            "${playerOne.score}" +
//                    "${playerTwo.score}"
//        )
}
//62fba88f3be87f1f56c581d7

