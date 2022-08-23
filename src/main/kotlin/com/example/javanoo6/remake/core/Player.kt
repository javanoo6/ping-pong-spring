package com.example.javanoo6.remake.core

import com.example.javanoo6.remake.core.impl.TablePoint
interface Player {
    val name: String
    val score: Int
    fun hit(): TablePoint
}