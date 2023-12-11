package ru.ifmo.soclosetoheaven.dto

import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min

data class PointRequest(
    @DecimalMin("-7.5")
    @DecimalMax("7.5")
    val x: Double,
    @DecimalMin("-7.5")
    @DecimalMax("7.5")
    val y: Double,
    @DecimalMin("1.0")
    @DecimalMax("5.0")
    val r: Double,
)