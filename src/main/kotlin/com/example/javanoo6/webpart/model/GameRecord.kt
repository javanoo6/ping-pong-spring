package com.example.javanoo6.webpart.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.days

@Document("gamerecords")
data class GameRecord (
        @Id
        val id:ObjectId = ObjectId(),
        val firstParticipant :Player?,
        val secondParticipant: Player?,
        val theWinner:Player?,
        val createdDate: LocalDateTime = LocalDateTime.now()
        )
{

}