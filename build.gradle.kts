import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.architectury.plugin.ArchitectPluginExtension
import groovy.json.StringEscapeUtils
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.task.RemapJarTask

plugins {
    java
    id("maven-publish")
    id("com.teamresourceful.resourcefulgradle") version "0.0.+"
    id("dev.architectury.loom") version "1.4-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
}

architectury {
    val minecraftVersion: String by project
    minecraft = minecraftVersion
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")

    val minecraftVersion: String by project
    val modLoader = project.name
    val modId = rootProject.name
    val isCommon = modLoader == rootProject.projects.common.name

    base {
        archivesName.set("$modId-$modLoader-$minecraftVersion")
    }

    configure<LoomGradleExtensionAPI> {
        silentMojangMappingsLicense()
    }

    repositories {
        maven(url = "https://maven.architectury.dev/")
        maven(url = "https://maven.minecraftforge.net/")
        maven(url = "https://maven.resourcefulbees.com/repository/maven-public/")
    }

    dependencies {
        val resourcefulLibVersion: String by project
        val resourcefulConfigVersion: String by project
        val jeiVersion: String by project
        val reiVersion: String by project

        "minecraft"("::$minecraftVersion")

        @Suppress("UnstableApiUsage")
        "mappings"(project.the<LoomGradleExtensionAPI>().layered {
            val parchmentVersion: String by project

            officialMojangMappings()

            parchment(create(group = "org.parchmentmc.data", name = "parchment-$minecraftVersion", version = parchmentVersion))
        })

        compileOnly(group = "com.teamresourceful", name = "yabn", version = "1.0.3")
        "modApi"(group = "com.teamresourceful.resourcefullib", name = "resourcefullib-$modLoader-$minecraftVersion", version = resourcefulLibVersion)
        "modApi"(group = "com.teamresourceful.resourcefulconfig", name = "resourcefulconfig-$modLoader-$minecraftVersion", version = resourcefulConfigVersion)
        if (isCommon) {
            "modApi"(group = "mezz.jei", name = "jei-$minecraftVersion-common-api", version = jeiVersion)
            "modCompileOnly"(group = "me.shedaniel", name = "RoughlyEnoughItems-api", version = reiVersion)
        }
    }

    java {
        withSourcesJar()
    }

    tasks.jar {
        archiveClassifier.set("dev")
    }

    tasks.named<RemapJarTask>("remapJar") {
        archiveClassifier.set(null as String?)
    }

    tasks.processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        filesMatching(listOf("META-INF/mods.toml", "fabric.mod.json")) {
            expand("version" to project.version)
        }
    }

    if (!isCommon) {
        apply(plugin = "com.github.johnrengelman.shadow")
        configure<ArchitectPluginExtension> {
            platformSetupLoomIde()
        }

        val shadowCommon by configurations.creating {
            isCanBeConsumed = false
            isCanBeResolved = true
        }

        tasks {
            "shadowJar"(ShadowJar::class) {
                archiveClassifier.set("dev-shadow")
                configurations = listOf(shadowCommon)
            }

            "remapJar"(RemapJarTask::class) {
                dependsOn("shadowJar")
                inputFile.set(named<ShadowJar>("shadowJar").flatMap { it.archiveFile })
            }
        }
    } else {
        sourceSets.main.get().resources.srcDir("src/main/generated/resources")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = "$modId-$modLoader-$minecraftVersion"
                from(components["java"])

                pom {
                    name.set("Extractinator $modLoader")
                    url.set("https://github.com/alexnijjar/$modId")

                    scm {
                        connection.set("git:https://github.com/alexnijjar/$modId.git")
                        developerConnection.set("git:https://github.com/alexnijjar/$modId.git")
                        url.set("https://github.com/alexnijjar/$modId")
                    }

                    licenses {
                        license {
                            name.set("ARR")
                        }
                    }
                }
            }
        }
        repositories {
            maven {
                setUrl("https://maven.resourcefulbees.com/repository/alexnijjar/")
                credentials {
                    username = System.getenv("MAVEN_USER")
                    password = System.getenv("MAVEN_PASS")
                }
            }
        }
    }
}

resourcefulGradle {
    templates {
        register("embed") {
            val minecraftVersion: String by project
            val version: String by project
            val changelog: String = file("changelog.md").readText(Charsets.UTF_8)

            source.set(file("templates/embed.json.template"))
            injectedValues.set(mapOf(
                "minecraft" to minecraftVersion,
                "version" to version,
                "changelog" to StringEscapeUtils.escapeJava(changelog),
            ))
        }
    }
}
