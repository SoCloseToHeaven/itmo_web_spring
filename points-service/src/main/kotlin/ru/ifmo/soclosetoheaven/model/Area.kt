package ru.ifmo.soclosetoheaven.model

interface Area {
    fun checkHit(x: Long, y: Long, r: Long) : Boolean
}