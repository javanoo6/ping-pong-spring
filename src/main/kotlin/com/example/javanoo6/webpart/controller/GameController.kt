package com.example.javanoo6.webpart.controller

import com.example.javanoo6.webpart.model.GameRecord
import com.example.javanoo6.webpart.service.GameRecordService
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/game")
class GameController {
    @Autowired
    lateinit var gameService: GameRecordService


    @GetMapping("/id/{id}")
    fun findFullGameById(@PathVariable id: String): Optional<GameRecord>? {
//        val gamePlayed = gameService.findById(id)
//        return ResponseEntity(gamePlayed, HttpStatus.OK)
        return gameService.findFullGameById(id)
    }

    @GetMapping("/name")
    fun findGameByName(@RequestParam name: String): Document {
//        val gamePlayed = gameService.findWinnerAndDateByName(name)
//        return ResponseEntity(gamePlayed, HttpStatus.OK)
        return gameService.findGameWinnerAndDateByName(name)
    }

    @GetMapping("/findById")
    fun findGameById(@RequestParam findById: ObjectId): Document {
//        val gamePlayed = gameService.findFullGameById(findById)
//        return ResponseEntity(gamePlayed, HttpStatus.OK)
        return gameService.findGameWinnerById(findById)
    }

}
