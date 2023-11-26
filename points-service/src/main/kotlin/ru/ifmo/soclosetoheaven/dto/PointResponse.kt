package ru.ifmo.soclosetoheaven.dto

import java.util.Date

data class PointResponse(
    val x: Double,
    val y: Double,
    val r: Double,
    val hit: Boolean,
    val createdAt: Date,
    val processingTime: Long,
)