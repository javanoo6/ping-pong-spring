package com.example.javanoo6.webpart.service

//import org.springframework.data.mongodb.core.query.where

import com.example.javanoo6.remake.core.impl.PlayerImpl
import com.example.javanoo6.webpart.model.GameRecord
import com.example.javanoo6.webpart.model.Player
import com.example.javanoo6.webpart.repository.GameRepository
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.aggregation.Fields
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service
import java.util.*


@Service
class GameRecordService {
    @Autowired
    lateinit var gameRepository: GameRepository

    @Autowired
    lateinit var template: MongoTemplate

    fun findWinnerAndDateByName(name: String): Document {

        val projectStage = project(Fields.fields("theWinner")).andExclude("_id").andInclude("createdDate")
        val matchStage = match(Criteria("theWinner.name").isEqualTo(name))
        val aggregation = newAggregation(GameRecord::class.java, matchStage, projectStage)//
        val output: AggregationResults<GameRecord> =
            template.aggregate(aggregation, "gamerecords", GameRecord::class.java)
        return output.rawResults
    }

    fun findFullGameById(id: ObjectId): Document {
        val project = project(Fields.fields("theWinner")).andExclude("_id").andInclude("createdDate")
        val match = match(Criteria("_id").isEqualTo(id))
        val aggregation = newAggregation(GameRecord::class.java, match, project)//
        val output: AggregationResults<GameRecord> =
            template.aggregate(aggregation, "gamerecords", GameRecord::class.java)
        return output.rawResults
    }

    fun saveGame(playerOne: PlayerImpl, playerTwo: PlayerImpl, theWinner: PlayerImpl) {
        gameRepository.save(
            GameRecord(
                firstParticipant = Player(name = playerOne.name, score = playerOne.score),
                secondParticipant = Player(name = playerTwo.name, score = playerTwo.score),
                theWinner = Player(name = theWinner.name, score = theWinner.score)
            )
        )

    }

    fun findById(id: String): Optional<GameRecord>? {
        return gameRepository.findById(id)

    }


}
