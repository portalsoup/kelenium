plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation(kotlin("reflect"))

    implementation("org.seleniumhq.selenium:selenium-java")
    testImplementation("org.junit.jupiter:junit-jupiter")
}
