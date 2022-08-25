package com.example.javanoo6.remake.core

import com.example.javanoo6.remake.core.impl.PlayerImpl

interface Game {
    fun run(playerOne: PlayerImpl, playerTwo: PlayerImpl, finalScore: Int)
//fun run(playerOne: PlayerImpl, playerTwo: PlayerImpl, finalScore: Int, gameRep: GameRecordService)
}