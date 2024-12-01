package org.jesperancinha.graphql.custom.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.graphql.execution.GraphQlSource
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class GraphQlConfig {

    @Bean
    fun graphQlSource(): GraphQlSource {
        return GraphQlSource.schemaResourceBuilder()
            .schemaResources(ClassPathResource("graphql/schema.graphqls"))
            .configureRuntimeWiring(runtimeWiringConfigurer())
            .build()
    }

    fun runtimeWiringConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { wiringBuilder ->
            wiringBuilder.type("Query") { typeWiring ->
                typeWiring.dataFetcher("myField") { environment ->
                    val name = environment.getArgument<String>("name")
                    if (name.isNullOrEmpty()) {
                        "Name argument is missing or empty"
                    } else {
                        "Hello, $name!"
                    }
                }
            }
        }
    }
}
