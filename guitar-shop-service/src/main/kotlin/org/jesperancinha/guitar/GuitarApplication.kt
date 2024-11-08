package org.jesperancinha.guitar

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GuitarApplication

fun main(args: Array<String>) {
    SpringApplication.run(GuitarApplication::class.java, *args)
}