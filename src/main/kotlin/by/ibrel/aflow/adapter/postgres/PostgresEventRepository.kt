package by.ibrel.aflow.adapter.postgres

import by.ibrel.aflow.domain.entity.Event
import by.ibrel.aflow.domain.entity.Events
import by.ibrel.aflow.domain.entity.ExposedEvent
import by.ibrel.aflow.domain.repository.EventRepository
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

internal class PostgresEventRepository : EventRepository {

    override suspend fun create(data: ExposedEvent): Event = transaction {
        Event.new {
            name = data.name
            type = data.type
            createdDate = data.creationDate.epochSecond
            user = data.user
        }
    }

    override suspend fun update(event: Event): Event {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: UUID): Event {
        try {
            return Event.find { Events.id eq id }.single()
        } catch (_: NoSuchElementException) {
            throw EntityNotFoundException(EntityID(id, Events), Event);
        }
    }
}
