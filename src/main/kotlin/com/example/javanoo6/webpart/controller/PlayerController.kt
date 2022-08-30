package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.request.PlayerRequest
import com.example.javanoo6.webpart.service.PlayerService
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/players")
class PlayerController(
    var playerService: PlayerService
) {


    @PostMapping("/playerNames")
    fun setPlayerNames(@RequestBody request: PlayerRequest): ResponseEntity<String> {
        val playerNames = playerService.setPlayersNames(request)
        return ResponseEntity(playerNames, HttpStatus.CREATED)
    }

    @GetMapping("/start")
    fun gameStart(@RequestParam finalScore: Int): ResponseEntity<String> {
        val gameStart = playerService.startGame(finalScore)
        return ResponseEntity.ok(gameStart)
    }

    @GetMapping("/id/{id}")
    fun findPlayerById(@PathVariable id: ObjectId): ResponseEntity<Player> {
        val foundPlayer = playerService.findById(id)
        return ResponseEntity(foundPlayer, HttpStatus.FOUND)
    }

    @GetMapping("/name/{name}")
    fun findPlayerByName(@PathVariable name: String): ResponseEntity<List<Player>> {
        val foundPlayerByName = playerService.findPlayerByName(name)

        return ResponseEntity(foundPlayerByName, HttpStatus.FOUND)
    }

}

