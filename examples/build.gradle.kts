plugins {
    kotlin("jvm") version "1.9.0"
}

group = "com.portalsoup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":framework"))
}

tasks.test {
    useJUnitPlatform()
}