val kotlinVersion: String by project
val logbackVersion: String by project
val postgresVersion: String by project
val exposedVersion: String by project
val mockkVersion: String by project
val hikaricpVersion: String by project
val koinKtor: String by project
val kafkaClient: String by project
val ktorVersion: String by project

plugins {
    kotlin("jvm") version "2.1.0-Beta2"
    id("io.ktor.plugin") version "3.0.0-eap-72"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
}

group = "by.ibrel"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("by.ibrel.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    maven { url = uri("https://jitpack.io") }
    mavenCentral()
}


dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-sse:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages")
    implementation("io.ktor:ktor-server-webjars-jvm")

    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("com.zaxxer:HikariCP:$hikaricpVersion")
    implementation("io.insert-koin:koin-ktor:$koinKtor")
    implementation("org.webjars:jquery:3.2.1")
    implementation("io.github.smiley4:ktor-swagger-ui:4.0.0")

    //kafka
    implementation("com.github.IlyaKalashnikov:ktor-kafka-client:-SNAPSHOT")
    implementation("org.apache.kafka:kafka-clients:$kafkaClient")
    implementation("org.apache.kafka:kafka-streams:$kafkaClient")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core")

    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-client-content-negotiation-jvm:$exposedVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks.test {
    useJUnitPlatform()
}