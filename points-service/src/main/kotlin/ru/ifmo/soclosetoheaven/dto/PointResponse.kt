package ru.ifmo.soclosetoheaven.dto

import java.util.Date

data class PointResponse(
    val id: Long,
    val x: Long,
    val y: Long,
    val r: Long,
    val hit: Boolean,
    val createdAt: Date,
    val processingTime: Long,
    val color: String,
)