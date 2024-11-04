package by.ibrel.aflow.module

import by.ibrel.aflow.adapter.postgres.PostgresEventRepository
import by.ibrel.aflow.application.event.EventApplication
import by.ibrel.aflow.application.event.impl.EventApplicationImpl
import by.ibrel.aflow.domain.repository.EventRepository
import io.ktor.server.application.*
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StoreQueryParameters
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.state.QueryableStoreTypes
import org.apache.kafka.streams.state.ReadOnlyWindowStore
import org.koin.dsl.module
import org.sour.cabbage.soup.KafkaStreamsConfig
import org.sour.cabbage.soup.kafkaStreams
import org.sour.cabbage.soup.producer
import java.time.Duration

fun kafkaModule (environment: ApplicationEnvironment) = module {

    val kafkaProducer = producer<String, String> (
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to environment.config.property("message.bootstrapServers").getString(),
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
        )
    )

//    val streams = kafkaStreams(
//        KafkaStreamsConfig(
//            topologyBuilder = topology(),
//            streamsConfig = streamsConfig(),
//            builder = StreamsBuilder()
//        )
//    )
//    streams.cleanUp()
//    streams.start()
//
//
//    environment.monitor.subscribe(ApplicationStopped) {
//        streams.close(Duration.ofSeconds(5))
//    }
//
//    val storage : ReadOnlyWindowStore<String, Long> =
//        streams.store(
//            StoreQueryParameters.fromNameAndType(
//                "windowed-word-count",
//                QueryableStoreTypes.windowStore()
//            )
//        )

    single { kafkaProducer }
//    single { streams }
//    single { storage }
}

fun Application.di() = module{
    single<EventRepository> {
        PostgresEventRepository()
    }

    single<EventApplication> {
        val repository by inject<EventRepository>()
        EventApplicationImpl(repository)
    }
}