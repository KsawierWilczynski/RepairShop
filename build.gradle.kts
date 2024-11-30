plugins {
    kotlin("jvm") version "1.9.23"
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
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}