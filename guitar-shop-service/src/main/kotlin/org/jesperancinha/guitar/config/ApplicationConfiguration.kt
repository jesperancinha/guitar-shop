package org.jesperancinha.guitar.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


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