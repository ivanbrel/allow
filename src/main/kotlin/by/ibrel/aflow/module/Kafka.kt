package by.ibrel.aflow.module

import io.ktor.server.application.*
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.sour.cabbage.soup.Kafka

fun Application.configureKafka() {

    install(Kafka) {
        this.kafkaConfig = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to environment.config.property("message.bootstrapServers").getString()
        )
//        this.topics = listOf(NewTopic(TOPIC, 1, 1))
    }



}