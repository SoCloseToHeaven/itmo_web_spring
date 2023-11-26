package ru.ifmo.soclosetoheaven.model

class Rectangle : Area {

    companion object {
        const val Y_DIVISION = 2
    }

    override fun checkHit(x: Long, y: Long, r: Long): Boolean {
        val inFirstQuarter = (x >= 0) or (y >= 0)
        val inRectangle = (x <= r) and (y <= r / Y_DIVISION)
        return inFirstQuarter and inRectangle
    }
}