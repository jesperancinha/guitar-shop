package org.jesperancinha.guitar

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

class ApolloGuitarClient {
   companion object{
       @JvmStatic
       fun main(args: Array<String>) {
           val apolloClient = ApolloClient.Builder()
               .serverUrl("http://localhost:8080/graphql")
               .build()

           apolloClient.findAndEchoGuitarInformation(1)
           apolloClient.findAndEchoGuitarInformation(2)
           apolloClient.findAndEchoGuitarInformation(3)
           exitProcess(0)
       }

       private fun ApolloClient.findAndEchoGuitarInformation(id: Int) = runBlocking {
           val response = query(_root_ide_package_.org.jesperancinha.guitar.gui.GuitarListQuery(id = Optional.present(id.toString())))
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