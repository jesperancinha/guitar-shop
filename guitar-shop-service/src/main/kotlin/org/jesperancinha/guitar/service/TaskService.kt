package org.jesperancinha.guitar.service

import org.jesperancinha.guitar.repository.Task
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration
import kotlin.random.Random

@Service
class TaskService {
    fun getTaskUpdates(projectId: String): Flux<Task> {
        return Flux.interval(Duration.ofSeconds(1))
            .map { fetchLatestTask(projectId) }
    }

    private fun fetchLatestTask(projectId: String): Task {
        return Task(id = Random.nextLong().toString(), title = "Task Title", status = "In Progress", projectId = projectId)
    }
}

