import Build_gradle.Version.Koin
import Build_gradle.Version.Kotlin
import Build_gradle.Version.KotlinResult
import Build_gradle.Version.KotlinSerialization
import Build_gradle.Version.Ktor
import Build_gradle.Version.Logback
import Build_gradle.Version.Mockk
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Version {
    const val Koin = "3.1.4"
    const val Kotlin = "1.6.10"
    const val KotlinResult = "1.1.14"
    const val KotlinSerialization = "1.3.2"
    const val Ktor = "1.6.7"
    const val Logback = "1.2.9"
    const val Mockk = "1.12.2"
}

plugins {
    application
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.chesire"
version = "0.0.1-SNAPSHOT"

application {
    mainClassName = "com.chesire.orochi.ApplicationKt"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$Logback")
    implementation("com.michael-bull.kotlin-result:kotlin-result:$KotlinResult")
    implementation("io.insert-koin:koin-core:$Koin")
    implementation("io.insert-koin:koin-ktor:$Koin")
    implementation("io.ktor:ktor-client-core:$Ktor")
    implementation("io.ktor:ktor-client-jetty:$Ktor")
    implementation("io.ktor:ktor-client-logging:$Ktor")
    implementation("io.ktor:ktor-client-serialization:$Ktor")
    implementation("io.ktor:ktor-serialization:$Ktor")
    implementation("io.ktor:ktor-server-core:$Ktor")
    implementation("io.ktor:ktor-server-host-common:$Ktor")
    implementation("io.ktor:ktor-server-netty:$Ktor")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$Kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$KotlinSerialization")

    testImplementation("io.insert-koin:koin-test:$Koin")
    testImplementation("io.insert-koin:koin-test-junit4:$Koin")
    testImplementation("io.ktor:ktor-client-mock:$Ktor")
    testImplementation("io.ktor:ktor-server-tests:$Ktor")
    testImplementation("io.mockk:mockk:$Mockk")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$Kotlin")
}

tasks {
    @Suppress("UnusedPrivateMember")
    val detektCheck by registering(Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/detekt.yml"))
        buildUponDefaultConfig = false
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}
