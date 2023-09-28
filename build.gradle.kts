import Build_gradle.ConstraintType.*

group = "com.portalsoup"
version = "1.0-SNAPSHOT"
val ktorVersion = "2.1.3"

enum class ConstraintType { PREFER, STRICT, REJECT, REQUIRE }
data class DepConstraint(val scope: String, val notation: String, val type: ConstraintType, val version: String)
object Versions {
    val ktor = "2.3.1"
    val exposed = "0.41.1"
    val log4j = "2.20.0"
}

val dependenciesList = listOf(
    DepConstraint("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core", PREFER, "1.7.3"),
    DepConstraint("implementation", "org.jetbrains.kotlinx:kotlinx-serialization-json", PREFER, "1.6.0"),
    DepConstraint("implementation", "org.seleniumhq.selenium:selenium-java", PREFER, "4.12.1"),
    DepConstraint("implementation", "org.apache.logging.log4j:log4j-api", PREFER, Versions.log4j),
    DepConstraint("implementation", "org.apache.logging.log4j:log4j-core", PREFER, Versions.log4j),
    DepConstraint("implementation", "org.apache.logging.log4j:log4j-slf4j-impl", PREFER, Versions.log4j),

    DepConstraint("testImplementation", "org.junit.jupiter:junit-jupiter", PREFER, "5.10.0-M1"),
    DepConstraint("testImplementation", "org.junit.jupiter:junit-jupiter-api", PREFER, "5.10.0-M1"),
    DepConstraint("testImplementation", "org.hamcrest:hamcrest", PREFER, "2.2")
)

allprojects {
    plugins.withType(JavaPlugin::class.java).whenPluginAdded {
        repositories {
            mavenCentral()
            maven {
                url = uri("https://m2.dv8tion.net/releases")
            }
            maven {
                url = uri("https://josm.openstreetmap.de/nexus/content/groups/public")
            }
        }

        tasks {
            getByName("test", Test::class) {
                useJUnitPlatform()
            }
        }

        // Apply versioning constraints to all java subprojects
        dependencies {
            constraints {
                dependenciesList.onEach {
                    add(it.scope, it.notation) {
                        version {
                            when (it.type) {
                                PREFER -> prefer(it.version)
                                STRICT -> strictly(it.version)
                                REJECT -> reject(it.version)
                                REQUIRE -> require(it.version)
                            }
                        }
                    }
                }
            }
        }
    }
}
