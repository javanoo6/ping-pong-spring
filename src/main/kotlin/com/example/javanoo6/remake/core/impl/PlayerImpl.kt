package com.example.javanoo6.remake.core.impl

import org.springframework.stereotype.Service
import com.example.javanoo6.remake.core.Player

//@Service
class PlayerImpl(
    val playerTablePoints: Set<TablePoint>,
    private val playerTableShouting: Set<TablePoint>,
    override val name: String,
    override var score: Int=0
) : Player {
    override fun hit(): TablePoint {
        return playerTableShouting.random()
    }
}