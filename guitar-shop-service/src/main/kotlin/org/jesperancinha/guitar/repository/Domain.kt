package org.jesperancinha.guitar.repository

import org.springframework.stereotype.Repository

data class User(
    val id: String,
    val name: String,
    val age: Int
)

data class Guitar(
    val id: Long,
    val brand: String,
    val model: String,
    val year: Int,
    val owner: Owner
)

data class Owner(
    val id: Long,
    val firstName: String,
    val lastName: String
)

data class Task(
    val id: String,
    val title: String,
    val status: String,
    val projectId: String
)

interface IRepository<T, ID> {
    fun findByIdOrNull(id: ID): T?
    fun findAll(): List<T>
}

@Repository
class GuitarRepository : IRepository<Guitar, Long> {
    override fun findByIdOrNull(id: Long) = guitars.firstOrNull { it.id == id }
    override fun findAll(): List<Guitar> = guitars

    companion object {
        val guitars: List<Guitar> = listOf(
            Guitar(1L, "Fender", "Stratocaster HSS Tidepool MN", 2018, OwnerRepository.owner1),
            Guitar(2L, "Jackson", "JS Series Dinky JS32Q DKA", 2013, OwnerRepository.owner2),
            Guitar(3L, "Gibson", "G5220 Electromatic Jet BT", 2018, OwnerRepository.owner1)
        )
    }
}

@Repository
class OwnerRepository : IRepository<Owner, Long> {
    override fun findByIdOrNull(id: Long) = owners.firstOrNull { it.id == id }
    override fun findAll(): List<Owner> = owners

    companion object {
        val owner1 = Owner(1L, "Joao", "Esperancinha")
        val owner2 = Owner(2L, "Betty", "Al")
        val owners = listOf(owner1, owner2)
    }
}
