package org.jesperancinha.guitar.controller

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin

@Controller
class GenericController {
    @QueryMapping
    fun hello(): String {
        return "Hello, world!"
    }
}
