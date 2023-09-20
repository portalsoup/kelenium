import Build_gradle.ConstraintType.*

group = "com.portalsoup"
version = "1.0-SNAPSHOT"
val ktorVersion = "2.1.3"

enum class ConstraintType { PREFER, STRICT, REJECT, REQUIRE }
data class DepConstraint(val scope: String, val notation: String, val type: ConstraintType, val version: String)
object Versions {
    val ktor = "2.3.1"
    val exposed = "0.41.1"
    val kotlinx = "1.7.3"
}

val dependenciesList = listOf(
    DepConstraint("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core", PREFER, Versions.kotlinx),
    DepConstraint("implementation", "org.jetbrains.kotlinx:kotlinx-serialization-json", PREFER, "1.6.0"),
    DepConstraint("implementation", "io.ktor:ktor-client-core", PREFER, Versions.ktor),
    DepConstraint("implementation", "io.ktor:ktor-client-cio", PREFER, Versions.ktor),
    DepConstraint("implementation", "io.ktor:ktor-client-content-negotiation", PREFER, Versions.ktor),
    DepConstraint("implementation", "io.ktor:ktor-serialization-kotlinx-json", PREFER, Versions.ktor),

    DepConstraint("testImplementation", "org.junit.jupiter:junit-jupiter", PREFER, "5.10.0-M1")
)

// Apply versioning constraints to all java subprojects
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
