package by.ibrel.aflow.module

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin


fun Application.configureDI() {
    install(Koin) {
        modules(kafkaModule(environment))
        modules(di())
    }
}