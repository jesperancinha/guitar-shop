package org.jesperancinha.guitar.service

import org.jesperancinha.guitar.repository.User
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class UserService {
    private val users = ConcurrentHashMap<String, User>()

    fun createUser(name: String, age: Int): User {
        val user = User(id = (users.size + 1).toString(), name = name, age = age)
        users[user.id] = user
        return user
    }

    fun getAllUsers(): List<User> = users.values.toList()
}