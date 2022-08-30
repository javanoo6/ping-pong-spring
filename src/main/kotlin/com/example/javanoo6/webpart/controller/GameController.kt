package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.model.GameRecord
import com.example.javanoo6.webpart.service.GameRecordService
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping("/game")
class GameController {
    @Autowired
    lateinit var gameService: GameRecordService


    @GetMapping("/id/{id}")
    fun findGameById(@PathVariable id: String): ResponseEntity<Optional<GameRecord>> {
        val gamePlayed = gameService.findById(id)
        return ResponseEntity.ok(gamePlayed)
    }

    @GetMapping("/name")
    fun findGameByName(@RequestParam name: String): ResponseEntity<Document> {
        val gamePlayed = gameService.findWinnerAndDateByName(name)
        return ResponseEntity(gamePlayed, HttpStatus.FOUND)
    }

    @GetMapping("/findById")
    fun findFullGameById(@RequestParam findById: ObjectId): ResponseEntity<Document> {
        val gamePlayed = gameService.findFullGameById(findById)
        return ResponseEntity(gamePlayed, HttpStatus.FOUND)
    }

}
