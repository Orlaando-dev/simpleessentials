plugins {
    kotlin("jvm") version "1.9.23" // Use the appropriate Kotlin version
    id("com.github.johnrengelman.shadow") version "8.0.0" // For packaging the plugin with dependencies
}

group = "me.orlaando"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") // PaperMC repository
    maven("https://repo.spongepowered.org/maven") // For Adventure API
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // PaperMC server API
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    // For Kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Adventure API for modern chat components
    compileOnly("net.kyori:adventure-api:4.14.0")
    compileOnly("net.kyori:adventure-platform-bukkit:4.3.2")

    //Command framework
    implementation("org.incendo:cloud-paper:2.0.0-beta.2")
    implementation("org.incendo:cloud-kotlin-coroutines-annotations:2.0.0-beta.2")
}

tasks {
    shadowJar {
        // Configure the shadowJar task to include your plugin's main class and version
        manifest {
            attributes(
                "Main-Class" to "me.orlaando.simpleessentials.Main",
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        }

        // Minimize the Jar to exclude unused classes
        minimize()
    }
}

tasks.named("build") {
    dependsOn("shadowJar")
}
