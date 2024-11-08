package org.jesperancinha.guitar.service

import org.jesperancinha.guitar.dto.OwnerDto
import org.jesperancinha.guitar.dto.toDto
import org.jesperancinha.guitar.repository.OwnerRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(
    val ownerRepository: OwnerRepository
) : IService<OwnerDto, Long> {
    override fun getByIdOrNull(id: Long): OwnerDto? = ownerRepository.findByIdOrNull(id)?.toDto()
}