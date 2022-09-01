package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.request.PlayerRequest
import com.example.javanoo6.webpart.service.PlayerService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/players")
class PlayerController {
    @Autowired
    lateinit var playerService: PlayerService

    @PostMapping("/playerNames")
    fun setPlayerNames(@RequestBody request: PlayerRequest): String {
//        val playerNames = playerService.setPlayersNames(request)
//        return ResponseEntity(playerNames, HttpStatus.CREATED)
        return playerService.setPlayersNames(request)
    }

    @GetMapping("/start")
    fun gameStart(@RequestParam finalScore: Int): String {
//        val gameStart = playerService.startGame(finalScore)
//        return ResponseEntity(gameStart, HttpStatus.ACCEPTED)
        return playerService.startGame(finalScore)
    }

    @GetMapping("/id/{id}")
    fun findPlayerById(@PathVariable id: ObjectId): Player {
//        val foundPlayer = playerService.findById(id)
//        return ResponseEntity(foundPlayer, HttpStatus.OK)
        return playerService.findById(id)
    }

    @GetMapping("/name/{name}")
    fun findPlayerByName(@PathVariable name: String): List<Player> {
//        val foundPlayerByName = playerService.findPlayerByName(name)
//
//        return ResponseEntity(foundPlayerByName, HttpStatus.OK)
        return playerService.findPlayerByName(name)

    }

}

