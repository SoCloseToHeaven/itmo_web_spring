package ru.ifmo.soclosetoheaven.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.ifmo.soclosetoheaven.dto.User


@FeignClient("auth-service")
interface AuthClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/user/me/info"])
    fun info(): User
}