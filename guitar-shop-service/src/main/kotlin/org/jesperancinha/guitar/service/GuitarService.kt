package org.jesperancinha.guitar.service

import org.jesperancinha.guitar.dto.GuitarDto
import org.jesperancinha.guitar.repository.GuitarRepository
import org.jesperancinha.guitar.dto.toDto
import org.springframework.stereotype.Service

@Service
class GuitarService(
    val repository: GuitarRepository
) : IService<GuitarDto, Long> {

    override fun getByIdOrNull(id: Long): GuitarDto? = repository.findByIdOrNull(id)?.toDto()
}