package com.example.javanoo6.remake.core

import org.springframework.stereotype.Service
import com.example.javanoo6.remake.core.impl.TablePoint
//@Service
interface Player {
    val name: String
    val score: Int
    fun hit(): TablePoint
}