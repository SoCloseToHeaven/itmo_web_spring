package ru.ifmo.soclosetoheaven.model

class Circle : Area {


    companion object {
        private const val R_DIVISION = 2
    }

    override fun checkHit(x: Long, y: Long, r: Long): Boolean {
        val inFourthQuarter = (x >= 0) and (y <= 0)
        val inCircle = (x * x) + (y * y) <= (r / R_DIVISION) * (r / R_DIVISION)
        return inFourthQuarter and inCircle
    }
}