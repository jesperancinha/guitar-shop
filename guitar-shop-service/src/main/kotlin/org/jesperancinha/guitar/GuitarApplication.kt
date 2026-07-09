package org.jesperancinha.guitar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GuitarApplication

fun main(args: Array<String>) {
    runApplication<GuitarApplication>(*args)
}