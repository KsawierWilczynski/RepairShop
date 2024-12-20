plugins {
    kotlin("jvm") version "1.9.23"
    // plugin for Dokka - KDoc generating tool
    id("org.jetbrains.dokka") version "1.9.20"
    jacoco
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    application
}

group = "ie.setu"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // https://mvnrepository.com/artifact/io.github.oshai/kotlin-logging-jvm
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("com.thoughtworks.xstream:xstream:1.4.21")
    implementation("org.codehaus.jettison:jettison:1.5.4")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
kotlin {
    jvmToolchain(16)
}
