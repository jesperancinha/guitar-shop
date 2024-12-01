package org.jesperancinha.guitar.controller

import org.jesperancinha.guitar.dto.OwnerDto
import org.jesperancinha.guitar.service.OwnerService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class OwnerController(
    val ownerService: OwnerService
) {

    @QueryMapping
    suspend fun ownerById(@Argument("id") id: Long): OwnerDto? = ownerService.getByIdOrNull(id)

}
