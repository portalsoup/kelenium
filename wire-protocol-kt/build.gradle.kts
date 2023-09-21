plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation(kotlin("reflect"))

    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")


//    implementation("io.ktor:ktor-client-core")
//    implementation("io.ktor:ktor-client-cio")
//    implementation("io.ktor:ktor-client-logging")
//    implementation("io.ktor:ktor-serialization-kotlinx-json")
//    implementation("io.ktor:ktor-client-content-negotiation")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

