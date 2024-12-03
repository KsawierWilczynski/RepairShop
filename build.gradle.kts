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
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("com.thoughtworks.xstream:xstream:1.4.21")
    implementation("org.codehaus.jettison:jettison:1.5.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}