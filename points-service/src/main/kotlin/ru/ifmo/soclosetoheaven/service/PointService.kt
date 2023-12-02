package ru.ifmo.soclosetoheaven.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.soclosetoheaven.dto.PointRequest
import ru.ifmo.soclosetoheaven.dto.PointResponse
import ru.ifmo.soclosetoheaven.repository.PointRepository
import ru.ifmo.soclosetoheaven.util.PointMapper
import java.util.*


@Service
class PointService {

    @Autowired
    private lateinit var pointRepository: PointRepository

    @Autowired
    private lateinit var pointMapper: PointMapper



    fun getAllPointsByCreatorId(creatorId: Long, currentR: Double) = pointRepository
        .findAllByCreatorId(creatorId)
        .map { entity -> pointMapper.mapToResponse(entity, currentR) }
        .toList()

    fun deleteAllUserPoints(creatorId: Long) = pointRepository.deleteAllByCreatorId(creatorId)



    fun createNewPoint(pointRequest: PointRequest, creatorId: Long) : PointResponse {
        val entity = pointMapper.mapToEntity(pointRequest, creatorId)
        pointRepository.save(entity)
        return pointMapper.mapToResponse(entity, pointRequest.r)
    }
}