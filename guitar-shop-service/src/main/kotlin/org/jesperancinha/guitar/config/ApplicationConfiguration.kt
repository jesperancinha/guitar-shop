package org.jesperancinha.guitar.config

import graphql.GraphQLContext
import graphql.GraphQLException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


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

class AuthenticationWebFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val authToken = exchange.request.headers.getFirst("Authorization") // Example: Extract from Authorization header
        val user = authenticateUser(authToken)
        val graphQLContext = GraphQLContext.newContext()
            .of("authenticatedUser", user)
            .build()
        exchange.attributes["graphql.context"] = graphQLContext
        return chain.filter(exchange)
    }

    private fun authenticateUser(authToken: String?): String {
        return "User Name"
    }
}

@Configuration
class WebConfig {

    @Bean
    fun authenticationFilter(): WebFilter {
        return AuthenticationWebFilter()
    }
}