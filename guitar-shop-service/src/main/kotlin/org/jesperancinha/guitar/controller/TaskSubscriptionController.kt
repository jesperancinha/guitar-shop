package org.jesperancinha.guitar.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.jesperancinha.guitar.repository.Task
import org.jesperancinha.guitar.service.TaskService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller

@Controller
class TaskSubscriptionController(
    val taskService: TaskService
) {

    @SubscriptionMapping
    fun taskUpdates(@Argument("projectId") projectId: String): Flow<Task> = taskService.getTaskUpdates(projectId).asFlow()
}
