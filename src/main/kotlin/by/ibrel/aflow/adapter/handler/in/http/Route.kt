package by.ibrel.aflow.adapter.handler.`in`.http

import by.ibrel.aflow.application.event.EventApplication
import by.ibrel.aflow.domain.entity.ExposedEvent
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.util.logging.Logger
import org.koin.ktor.ext.get
import org.slf4j.LoggerFactory
import java.util.UUID

fun Application.configureRouting(eventApplication: EventApplication = get()) {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    routing {
        route("/events") {
            get("/{id}") {
                try {
                    val id =
                        call.parameters["id"]
                            ?: throw IllegalArgumentException("Required path param 'name' is absent.")
                    eventApplication.find(UUID.fromString(id))?.let {
                        call.respond(HttpStatusCode.Created, it)
                    } ?: call.respond(HttpStatusCode.Created)

                } catch (_: Exception) {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            post {
                val event = call.receive<ExposedEvent>()
                try {
                    val savedEvent = eventApplication.post(event)
                    call.respond(HttpStatusCode.Created, savedEvent)
                } catch (e: Exception) {
                    logger.error(e.message)
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}