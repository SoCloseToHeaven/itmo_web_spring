package ru.ifmo.soclosetoheaven.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.ifmo.soclosetoheaven.dto.PointRequest
import ru.ifmo.soclosetoheaven.dto.PointResponse
import ru.ifmo.soclosetoheaven.repository.PointRepository
import ru.ifmo.soclosetoheaven.util.PointMapper


@Service
class PointService(
    private val pointRepository: PointRepository,
    private val pointMapper: PointMapper,
) {
    @Transactional
    fun getAllPointsByCreatorId(creatorId: Long, currentR: Double) = pointRepository
        .findAllByCreatorId(creatorId)
        .map { entity -> pointMapper.mapToResponse(entity, currentR) }
        .toList()


    @Transactional
    fun deleteAllUserPoints(creatorId: Long) = pointRepository.deleteAllByCreatorId(creatorId)


    @Transactional
    fun createNewPoint(pointRequest: PointRequest, creatorId: Long) : PointResponse {
        val entity = pointMapper.mapToEntity(pointRequest, creatorId)
        pointRepository.save(entity)
        return pointMapper.mapToResponse(entity, pointRequest.r)
    }
}