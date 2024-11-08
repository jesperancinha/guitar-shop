package org.jesperancinha.guitar.service

interface IService<T, ID> {
    fun getByIdOrNull(id: ID): T?
}