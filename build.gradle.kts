plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
}

repositories {
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    compile("org.seleniumhq.selenium:selenium-java:3.141.59")
//    compile("io.github.bonigarcia:webdrivermanager:4.1.0")
    compile("org.json:json:20200518")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
