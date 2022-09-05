package com.example.javanoo6.webpart.core

import com.example.javanoo6.webpart.core.impl.TablePoint
interface Player {
    val name: String?
    val score: Int
    fun hit(): TablePoint
}