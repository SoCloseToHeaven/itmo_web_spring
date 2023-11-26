package ru.ifmo.soclosetoheaven.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.ifmo.soclosetoheaven.entity.PointEntity

@Repository
interface PointRepository : JpaRepository<PointEntity, Long> {
    fun findAllByCreatorId(creatorId: Long) : List<PointEntity>
    fun deleteAllByCreatorId(creatorId: Long)
}