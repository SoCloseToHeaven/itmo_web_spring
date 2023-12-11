package ru.ifmo.soclosetoheaven.dto

import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin

data class PointRequest(
    @field:DecimalMin("-7.5")
    @field:DecimalMax("7.5")
    val x: Double,
    @field:DecimalMin("-7.5")
    @field:DecimalMax("7.5")
    val y: Double,
    @field:DecimalMin("1.0")
    @field:DecimalMax("5.0")
    val r: Double,
)