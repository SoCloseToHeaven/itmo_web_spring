package ru.ifmo.soclosetoheaven.model

class Rectangle : Area {

    companion object {
        const val Y_DIVISION = 2
    }

    override fun checkHit(x: Double, y: Double, r: Double) : Boolean {
        val inFirstQuarter = (x >= 0) and  (y >= 0)
        val inRectangle = (x <= r) and (y <= r / Y_DIVISION)
        return inFirstQuarter and inRectangle
    }
}