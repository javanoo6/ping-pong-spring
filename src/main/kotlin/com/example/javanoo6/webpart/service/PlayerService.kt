package com.example.javanoo6.webpart.service

import com.example.javanoo6.webpart.exceptions.NotFoundException
import com.example.javanoo6.webpart.model.GameRecord
import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.repository.PlayerRepository
import com.example.javanoo6.webpart.request.PlayerRequest
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.lookup
import org.springframework.data.mongodb.core.aggregation.Aggregation.project
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.aggregation.Fields
import org.springframework.data.mongodb.core.aggregation.Fields.field
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import java.util.*


@Service
class PlayerService(
    private val playerRepository: PlayerRepository,
    private val template: MongoTemplate

) {
    fun createUser(request: PlayerRequest): String {
        val newUser = request.name.let { playerRepository.findPlayerByName(it) }
        if (newUser.isNotEmpty()) {
            return request.name
        } else playerRepository.save(
            Player(
                name = request.name,
                score = null
            )
        )
        return request.name
    }

    fun findById(id: ObjectId): Player=
        playerRepository.findById(id).orElseThrow{(NotFoundException("такого игрока с $id не найдено "))}



}

/*
    fun findGamesByName(name:String): Document {
        var lookUp = lookup("gamerecords","name","theWinner.name","gamesWon")
        var project = project (Fields.fields("theWinner")).andExclude("_id").andInclude("createdDate")

        val aggregation = Aggregation.newAggregation(GameRecord::class.java, lookUp, project)//
        val output: AggregationResults<GameRecord> = template.aggregate(aggregation, "players", GameRecord::class.java)
        return output.rawResults
    }
*/