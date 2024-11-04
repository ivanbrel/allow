package by.ibrel

import by.ibrel.aflow.adapter.handler.`in`.http.configureRouting
import by.ibrel.aflow.module.configureDB
import by.ibrel.aflow.module.configureDI
import by.ibrel.aflow.module.configureKafka
import by.ibrel.aflow.module.configureSee
import by.ibrel.aflow.module.configureSerialization
import by.ibrel.aflow.module.configureStatusPage
import by.ibrel.aflow.module.configureSwagger
import io.ktor.server.application.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
//    configureMonitoring()
    configureDI()
    configureSerialization()
    configureSwagger()
    configureDB()
    configureKafka()
//    configureSee()
    configureRouting()
    configureStatusPage()
}