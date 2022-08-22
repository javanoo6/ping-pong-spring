package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.repository.GameRepository
import com.example.javanoo6.webpart.repository.PlayerRepository
import com.example.javanoo6.webpart.request.PlayerRequest
import com.example.javanoo6.webpart.service.PlayerService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.javanoo6.remake.core.impl.GameImpl
import com.example.javanoo6.remake.core.impl.PingPongTableImpl
import com.example.javanoo6.remake.core.impl.PlayerImpl
import com.example.javanoo6.webpart.service.GameRecordService
import org.bson.Document

@RestController
@RequestMapping("/players")
class PlayerController(
    private val playerRepository: PlayerRepository,
    private val playerService: PlayerService,
    private val gameRepSer: GameRecordService
) {

    val pingPongTable = PingPongTableImpl()
    lateinit var playerOne: PlayerImpl
    lateinit var playerTwo: PlayerImpl
//    var finalScore: Int = 11
    val game= GameImpl()


    @PostMapping("/first")
    fun setPlayerOneName(@RequestBody request: PlayerRequest): ResponseEntity<String> {
        val playerSer = playerService.createUser(request)

        @Autowired
        playerOne = PlayerImpl(
            pingPongTable.playerOneTablePoints,
            pingPongTable.playerOneTablePointsForShouting,
            playerSer
        )
        return ResponseEntity(playerSer, HttpStatus.CREATED)
    }

    @PostMapping("/second")
    fun setPlayerTwoName(@RequestBody request: PlayerRequest): ResponseEntity<String> {
        val playerSer = playerService.createUser(request)

        @Autowired
        playerTwo = PlayerImpl(
            pingPongTable.playerTwoTablePoints,
            pingPongTable.playerTwoTablePointsForShouting,
            playerSer
        )
        return ResponseEntity(playerSer, HttpStatus.CREATED)
    }


    @GetMapping("/start")
    fun gameStart(@RequestParam finalScore: Int) {

        game.run(playerOne, playerTwo, finalScore,gameRepSer)

    }
    @GetMapping("/id/{id}")
    fun findPlayerById(@PathVariable id:ObjectId): ResponseEntity<Player> {
        var foundPlayer = playerService.findById(id)
        return ResponseEntity(foundPlayer,HttpStatus.FOUND)
    }
    @GetMapping("/name/{name}")
    fun findPlayerByName(@PathVariable name:String): ResponseEntity<List<Player>> {
        var foundPlayerByName = playerRepository.findPlayerByName(name)

        return  ResponseEntity(foundPlayerByName,HttpStatus.FOUND)
    }

    @GetMapping("/game")
    fun findGamesByName(@RequestParam playersName:String): ResponseEntity<Document> {
    var playerFound = playerService.findGamesByName(playersName)
        return ResponseEntity.ok(playerFound)
    }

}