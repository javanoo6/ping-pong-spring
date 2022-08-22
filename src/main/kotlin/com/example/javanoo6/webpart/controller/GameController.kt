package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.model.GameRecord
import com.example.javanoo6.webpart.repository.GameRepository
import com.example.javanoo6.webpart.service.GameRecordService
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/game")
class GameController(
    var gameService: GameRecordService,
    var gameRepository: GameRepository
) {

    @GetMapping("/id/{id}")
    fun findGameById(@PathVariable id: String): ResponseEntity<Optional<GameRecord>> {
        val gamePlayed = gameRepository.findById(id)
        return ResponseEntity.ok(gamePlayed)
    }

    @GetMapping("/name")
    fun findGameByName2(@RequestParam name: String): ResponseEntity<Document> {
        val gamePlayed = gameService.findWinnerAndDateByName(name)
        return ResponseEntity(gamePlayed, HttpStatus.FOUND)
    }

    @GetMapping("/findById")
    fun findGameById(@RequestParam findById: ObjectId): ResponseEntity<Document> {
        val gamePlayed = gameService.findGameById(findById)
        return ResponseEntity(gamePlayed, HttpStatus.FOUND)
    }

}
