package ru.ifmo.soclosetoheaven.model

class Triangle : Area {


    companion object {
        private const val R_DIVISION = 2
    }
    override fun checkHit(x: Double, y: Double, r: Double) : Boolean {
        val inThirdQuarter = (x <= 0) and (y <= 0)
        val inTriangle = y >= -x - (r / R_DIVISION)
        return inThirdQuarter and inTriangle
    }
}