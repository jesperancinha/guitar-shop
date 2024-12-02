package org.jesperancinha.guitar.controller

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.time.LocalDate

data class Event(
    val name: String,
    val date: LocalDate
)

@Controller
class GenericController {
    @QueryMapping
    fun hello(): String {
        return "Hello, world!"
    }

    private val events = mutableListOf<Event>()

    @QueryMapping
    fun getEvents(): List<Event> = events

    @MutationMapping
    fun addEvent(@Argument name: String, @Argument date: LocalDate): Event {
        val event = Event(name, date)
        events.add(event)
        return event
    }
}
