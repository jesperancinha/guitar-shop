package org.jesperancinha.guitar.controller

import graphql.GraphQLContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.jesperancinha.guitar.repository.User
import org.jesperancinha.guitar.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserController(private val userService: UserService) {

    @MutationMapping
    suspend fun createUser(@Argument name: String, @Argument age: Int): User {
        return userService.createUser(name, age)
    }

    @QueryMapping
    fun allUsers(): Flow<User> {
        return userService.getAllUsers().asFlow()
    }

    @QueryMapping
    fun getUserInfo(context: GraphQLContext): String {
        val authenticatedUser = context.get<String>("authenticatedUser")
        return authenticatedUser ?: "Anonymous"
    }
}
