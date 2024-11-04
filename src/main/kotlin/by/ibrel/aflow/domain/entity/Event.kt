package by.ibrel.aflow.domain.entity

import by.ibrel.aflow.module.InstantSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.time.Instant
import java.util.UUID

object Events : UUIDTable() {
    val name = varchar("name", 10).uniqueIndex()
    val type = varchar("type", 5)
    val createdDate = long("creation_date")
    val user = varchar("user", 10)
}

class Event(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<Event>(Events)

    var name by Events.name
    var type by Events.type
    var createdDate by Events.createdDate
    var user by Events.user
}

@Serializable
data class ExposedEvent(
    val name: String,
    val type: String,
    @Serializable(with = InstantSerializer::class)
    val creationDate: Instant,
    val user: String
) {
    constructor(entity: Event) : this(entity.name, entity.type, entity.createdDate.toDate(), entity.user)
}

fun Long.toDate() = Instant.ofEpochSecond(this)