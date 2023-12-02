package ru.ifmo.soclosetoheaven.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
    fun newPoint(@RequestBody request: PointRequest) = pointService.createNewPoint(request, jwtDTO.userId)


    @GetMapping( "/{current_r}")
    fun getPoints(@PathVariable("current_r") r: Double) = pointService.getAllPointsByCreatorId(jwtDTO.userId, r)

    @DeleteMapping
    fun deleteAllPoints() = pointService.deleteAllUserPoints(jwtDTO.userId)
}