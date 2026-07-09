package org.jesperancinha.graphql.custom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GuitarCustomServiceLauncher
fun main(args: Array<String>) {
    runApplication<GuitarCustomServiceLauncher>(*args)
}