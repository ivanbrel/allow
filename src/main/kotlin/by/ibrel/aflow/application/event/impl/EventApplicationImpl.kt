package by.ibrel.aflow.application.event.impl

import by.ibrel.aflow.application.event.EventApplication
import by.ibrel.aflow.domain.entity.ExposedEvent
import by.ibrel.aflow.domain.repository.EventRepository
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import java.util.UUID

class EventApplicationImpl(private val eventRepository: EventRepository) : EventApplication {

    override suspend fun post(event: ExposedEvent) = ExposedEvent(eventRepository.create(event))

    override suspend fun find(id: UUID): ExposedEvent? {
        return try {
            ExposedEvent(eventRepository.get(id))
        } catch (_: EntityNotFoundException) {
            null
        }
    }
}