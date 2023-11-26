package ru.ifmo.soclosetoheaven.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date


@Entity(name = "points")
@Table(name = "points")
data class PointEntity(
    @Column(name = "x")
    var x: Double,

    @Column(name = "y")
    var y: Double,

    @Column(name = "processing_time")
    var processingTime: Long,

    @Column(name = "created_at")
    var createdAt: Date,


    @Column(name = "creator_id")
    var creatorId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
}