package ru.ifmo.soclosetoheaven.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import org.springframework.web.bind.annotation.*
import ru.ifmo.soclosetoheaven.dto.JwtDTO
import ru.ifmo.soclosetoheaven.dto.PointRequest
import ru.ifmo.soclosetoheaven.service.PointService


@RestController
@RequestMapping("/points")
class PointsController(
    val pointService: PointService,
    var jwtDTO: JwtDTO,
) {

    @PostMapping
    fun newPoint(@Valid @RequestBody request: PointRequest) = pointService.createNewPoint(request, jwtDTO.userId)


    @GetMapping( "/{current_r}")
    fun getPoints(
        @PathVariable("current_r")
        @Valid
        @DecimalMin("1.0")
        @DecimalMax("5.0")
        r: Double
    ) = pointService.getAllPointsByCreatorId(jwtDTO.userId, r)

    @DeleteMapping
    fun deleteAllPoints() = pointService.deleteAllUserPoints(jwtDTO.userId)
}