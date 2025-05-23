package org.jesperancinha.graphql.custom

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GuitarCustomServiceLauncher
fun main(args: Array<String>) {
    SpringApplication.run(GuitarCustomServiceLauncher::class.java, *args)
}