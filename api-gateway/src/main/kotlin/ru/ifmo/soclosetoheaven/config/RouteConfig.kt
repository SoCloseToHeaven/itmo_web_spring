package ru.ifmo.soclosetoheaven.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.ifmo.soclosetoheaven.filter.GatewayJWTFilter


@Configuration
class RouteConfig {



    @Bean
    fun routeLocator(builder: RouteLocatorBuilder, jwtFilter: GatewayJWTFilter): RouteLocator = builder
        .routes()
        .route("points-route") { r ->
            r.path("/points/**").filters {f -> f.filter(jwtFilter)}.uri("lb://points-service")
        }
        .route("auth-route") { r -> r.path("/auth/**").uri("lb://auth-service") }
        .route("user-route") { r -> r.path("/user/**").uri("lb://auth-service") }
        .build()
}