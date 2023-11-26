package ru.ifmo.soclosetoheaven.model

interface Area {
    fun checkHit(x: Double, y: Double, r: Double) : Boolean
}