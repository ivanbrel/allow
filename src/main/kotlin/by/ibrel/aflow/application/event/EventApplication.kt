package by.ibrel.aflow.application.event

import by.ibrel.aflow.domain.entity.ExposedEvent
import java.util.UUID

interface EventApplication {

    suspend fun post(event: ExposedEvent): ExposedEvent

    suspend fun find(id: UUID): ExposedEvent?
}