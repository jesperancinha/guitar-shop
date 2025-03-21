package org.jesperancinha.guitar

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jesperancinha.guitar.gui.GuitarListQuery
import org.jesperancinha.guitar.gui.TaskListSubscription
import org.jesperancinha.guitar.gui.type.Subscription
import kotlin.system.exitProcess

class ApolloGuitarClient {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit {
            runBlocking(Dispatchers.IO) {

                val apolloClient = ApolloClient.Builder()
                    .serverUrl("http://localhost:8080/graphql")
                    .build()

                apolloClient.findAndEchoGuitarInformation(1)
                apolloClient.findAndEchoGuitarInformation(2)
                apolloClient.findAndEchoGuitarInformation(3)
                apolloClient.createSubscription()
            }
            exitProcess(0)
        }

        private suspend fun ApolloClient.createSubscription() {
            val response = subscription(TaskListSubscription(projectId = "123"))
                .execute().data
            println(response)
        }

        private suspend fun ApolloClient.findAndEchoGuitarInformation(id: Int) {
            val response =
                query(GuitarListQuery(id = Optional.present(id.toString())))
                    .execute()
            if (response.hasErrors()) {
                println("Error fetching guitar: ${response.errors}")
            } else {
                val guitar = response.data?.guitarById
                println("Guitar ID: ${guitar?.id}")
                println("Guitar Brand: ${guitar?.brand}")
                println("Guitar Model: ${guitar?.model}")
                println("Guitar year: ${guitar?.year}")
                println("Guitar firstname: ${guitar?.owner?.firstName}")
            }
        }
    }
}