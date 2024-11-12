package org.jesperancinha.guitar.controller

import org.jesperancinha.guitar.service.GuitarService
import org.jesperancinha.guitar.service.OwnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Controller
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class GuitarControllerTest(@Autowired val webTestClient: WebTestClient) {

    @Test
    fun `should fetch guitar by id`() {
        val query = """
            query {
                guitarById(id: "1") {
                    id
                    brand
                    model
                    year
                    owner {
                        id
                        firstName
                        lastName
                    }
                }
            }
        """

        webTestClient.post()
            .uri("/graphql")
            .bodyValue(mapOf("query" to query))
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.data.guitarById.id").isEqualTo("1")
            .jsonPath("$.data.guitarById.brand").isEqualTo("Fender")
            .jsonPath("$.data.guitarById.model").isEqualTo("Stratocaster HSS Tidepool MN")
            .jsonPath("$.data.guitarById.year").isEqualTo(2018)
            .jsonPath("$.data.guitarById.owner.id").isEqualTo("1")
            .jsonPath("$.data.guitarById.owner.firstName").isEqualTo("Joao")
            .jsonPath("$.data.guitarById.owner.lastName").isEqualTo("Esperancinha")
    }

    @Test
    fun `should fetch owner by id`() {
        val query = """
            query {
                ownerById(id: "1") {
                    id
                    firstName
                    lastName
                }
            }
        """

        webTestClient.post()
            .uri("/graphql")
            .bodyValue(mapOf("query" to query))
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.data.ownerById.id").isEqualTo("1")
            .jsonPath("$.data.ownerById.firstName").isEqualTo("Joao")
            .jsonPath("$.data.ownerById.lastName").isEqualTo("Esperancinha")
    }
}