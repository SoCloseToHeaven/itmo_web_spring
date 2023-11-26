package ru.ifmo.soclosetoheaven.model

import org.springframework.stereotype.Component


@Component
class HitChecker : Area {

    private val areas = listOf(
        Circle(),
        Rectangle(),
        Triangle()
    )

    override fun checkHit(x: Double, y: Double, r: Double) : Boolean= areas.any { area -> area.checkHit(x,y,r) }
}