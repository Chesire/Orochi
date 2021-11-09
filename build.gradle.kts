import Build_gradle.Version.Kotlin
import Build_gradle.Version.KotlinSerialization
import Build_gradle.Version.Ktor
import Build_gradle.Version.Logback
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Version {
    const val Logback = "1.2.6"
    const val Ktor = "1.6.5"
    const val Kotlin = "1.5.31"
    const val KotlinSerialization = "1.3.0"
}

plugins {
    application
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.chesire"
version = "0.0.1-SNAPSHOT"

val appStart = "com.chesire.orochi.ApplicationKt"
application {
    mainClassName = appStart
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$Logback")
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
    testImplementation("io.ktor:ktor-server-tests:$Ktor")
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
