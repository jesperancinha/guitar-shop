package org.jesperancinha.guitar.config

import graphql.GraphQLException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.server.WebGraphQlInterceptor
import org.springframework.graphql.server.WebGraphQlRequest
import org.springframework.graphql.server.WebGraphQlResponse
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import reactor.core.publisher.Mono
import java.util.*


@Configuration
@EnableWebFluxSecurity
class ReactiveSecurityConfig {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange { exchanges ->
                exchanges.pathMatchers("/graphql").permitAll()
                exchanges.anyExchange().permitAll()
            }
            .csrf { it.disable() }

        return http.build()
    }
}

class GuitarNotFoundException : RuntimeException() {
    override val message: String = "No guitar found!"
}

class OwnerNotFoundException : RuntimeException() {
    override val message: String = "No owner found!"
}

@ControllerAdvice
class GraphQLExceptionHandler {

    @ExceptionHandler(GuitarNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleGuiterNotFoundException(ex: GuitarNotFoundException): List<GraphQLException> {
        return listOf(
            GraphQLException("Custom not found exception: ${ex.message}")
        )
    }

    @ExceptionHandler(OwnerNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleOwnerNotFoundException(ex: OwnerNotFoundException): List<GraphQLException> {
        return listOf(
            GraphQLException("Custom not found exception: ${ex.message}")
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): List<GraphQLException> {
        return listOf(
            GraphQLException("General error: ${ex.message}")
        )
    }
}

@Component
class CustomGraphQlInterceptor : WebGraphQlInterceptor {

    val decoder: Base64.Decoder by lazy { Base64.getDecoder() }

    override fun intercept(request: WebGraphQlRequest, chain: WebGraphQlInterceptor.Chain): Mono<WebGraphQlResponse?> {
        val user = extractUser(request)
        return chain.next(request).contextWrite { context ->
            context.put("authenticatedUser", user)
        }
    }

    private fun extractUser(request: WebGraphQlRequest): String {
        return request.headers.getFirst("Authorization")
            ?.run { String(decoder.decode(this.replace("Basic ", "").trim())).split(":")[0] } ?: "Anonymous"
    }
}