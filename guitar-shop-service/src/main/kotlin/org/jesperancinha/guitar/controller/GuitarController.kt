package org.jesperancinha.guitar.controller

import org.jesperancinha.guitar.config.GuitarNotFoundException
import org.jesperancinha.guitar.dto.GuitarDto
import org.jesperancinha.guitar.dto.OwnerDto
import org.jesperancinha.guitar.service.GuitarService
import org.jesperancinha.guitar.service.OwnerService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller


@Controller
class GuitarController(
    val guitarService: GuitarService,
    val ownerService: OwnerService
) {

    @QueryMapping
    suspend fun guitarById(@Argument("id") id: Long) = guitarService.getByIdOrNull(id) ?: throw GuitarNotFoundException()

    @SchemaMapping
    suspend fun owner(guitarDto: GuitarDto): OwnerDto? =
        ownerService.getByIdOrNull(guitarDto.ownerId)
}