package by.ibrel.aflow.domain.repository

import by.ibrel.aflow.domain.entity.Event
import by.ibrel.aflow.domain.entity.ExposedEvent
import java.util.UUID

interface EventRepository {

    suspend fun create(data: ExposedEvent) : Event

    suspend fun update(event: Event): Event

    suspend fun get(id: UUID) : Event

}