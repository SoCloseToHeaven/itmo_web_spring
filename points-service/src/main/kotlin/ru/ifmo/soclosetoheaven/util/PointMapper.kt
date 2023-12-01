package ru.ifmo.soclosetoheaven.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.ifmo.soclosetoheaven.dto.PointRequest
import ru.ifmo.soclosetoheaven.dto.PointResponse
import ru.ifmo.soclosetoheaven.entity.PointEntity
import ru.ifmo.soclosetoheaven.model.HitChecker
import java.util.*


@Component

class PointMapper {

    @Autowired
    private lateinit var hitChecker: HitChecker

    fun mapToEntity(point: PointRequest, creatorId: Long) : PointEntity {
        val startTime = System.nanoTime()
        return PointEntity(
            point.x,
            point.y,
            System.nanoTime() - startTime,
            Date(),
            creatorId
        )
    }

    fun mapToResponse(point: PointEntity, currentR: Double) = PointResponse(
        point.x,
        point.y,
        currentR,
        hitChecker.checkHit(point.x, point.y, currentR),
        point.createdAt,
        point.processingTime
    )
}