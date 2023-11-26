package ru.ifmo.soclosetoheaven.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.ifmo.soclosetoheaven.dto.PointRequest
import ru.ifmo.soclosetoheaven.dto.PointResponse
import ru.ifmo.soclosetoheaven.entity.PointEntity
import ru.ifmo.soclosetoheaven.model.HitChecker
import ru.ifmo.soclosetoheaven.repository.PointRepository
import java.util.*


@Service
class PointService {

    @Autowired
    private lateinit var pointRepository: PointRepository

    @Autowired
    private lateinit var hitChecker: HitChecker



    fun getAllPointsByCreatorId(creatorId: Long, currentR: Double) = pointRepository
        .findAllByCreatorId(creatorId)
        .map { entity -> mapToPointResponse(entity, currentR) }
        .toList()

    fun deleteAllUsersPoints(creatorId: Long) = pointRepository.deleteAllByCreatorId(creatorId)



    fun createNewPoint(pointRequest: PointRequest, creatorId: Long) : PointResponse {
        val entity = newPointEntityByPointRequest(pointRequest, creatorId)
        pointRepository.save(entity)
        return mapToPointResponse(entity, pointRequest.r)
    }


    private fun mapToPointResponse(pointEntity: PointEntity, currentR: Double) = PointResponse(
        pointEntity.x,
        pointEntity.y,
        currentR,
        hitChecker.checkHit(pointEntity.x, pointEntity.y, currentR),
        pointEntity.createdAt,
        pointEntity.processingTime
    )


    private fun newPointEntityByPointRequest(pointRequest: PointRequest, creatorId: Long) : PointEntity {
        val startTime = System.nanoTime()
        return PointEntity(
            pointRequest.x,
            pointRequest.y,
            System.nanoTime() - startTime,
            Date(),
            creatorId
        )
    }
}