package org.jesperancinha.guitar.dto

import org.jesperancinha.guitar.repository.Guitar
import org.jesperancinha.guitar.repository.Owner

class GuitarDto(
    val id: Long,
    val brand: String,
    val model: String,
    val year: Int,
    val ownerId: Long
)

class OwnerDto(
    val id: Long,
    val firstName: String,
    val lastName: String
)

fun Guitar.toDto() = GuitarDto(
    id = id,
    brand = brand,
    model = model,
    year = year,
    ownerId = owner.id
)

fun Owner.toDto() = OwnerDto(
    id = id,
    firstName = firstName,
    lastName = lastName
)

//fun GuitarDto.toModel() = Guitar(
//    id = id,
//    brand = brand,
//    model = model,
//    year = year,
//    owner = owner.toModel()
//)

//fun OwnerDto.toModel() = Owner(
//    id = id,
//    firstName = firstName,
//    lastName = lastName
//)