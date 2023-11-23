package ru.ifmo.soclosetoheaven

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class PointsServiceApplication

fun main(args: Array<String>) {
	runApplication<PointsServiceApplication>(*args)
}
