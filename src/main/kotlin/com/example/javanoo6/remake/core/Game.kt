package com.example.javanoo6.remake.core

import com.example.javanoo6.remake.core.impl.PlayerImpl
import com.example.javanoo6.webpart.service.GameRecordService

interface Game {
    fun run(playerOne: PlayerImpl, playerTwo: PlayerImpl, finalScore: Int, gameRep: GameRecordService)
}