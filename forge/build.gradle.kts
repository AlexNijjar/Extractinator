architectury {
    forge()
}

loom {
    forge {
        mixinConfig("extractinator.mixins.json")
    }
    runs {
        create("data") {
            data()
            programArgs("--all", "--mod", "extractinator")
            programArgs("--output", project(":common").file("src/main/generated/resources").absolutePath)
            programArgs("--existing", project(":common").file("src/main/resources").absolutePath)
        }
    }
}

val common: Configuration by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentForge"].extendsFrom(this)
}

dependencies {
    common(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(path = ":common", configuration = "transformProductionForge")) {
        isTransitive = false
    }

    val minecraftVersion: String by project
    val forgeVersion: String by project
    val reiVersion: String by project

    forge(group = "net.minecraftforge", name = "forge", version = "$minecraftVersion-$forgeVersion")

    modCompileOnly(group = "me.shedaniel", name = "RoughlyEnoughItems-api-forge", version = reiVersion)
    modLocalRuntime(group = "me.shedaniel", name = "RoughlyEnoughItems-forge", version = reiVersion)
    modCompileOnly(group = "me.shedaniel", name = "RoughlyEnoughItems-default-plugin", version = reiVersion)

    modLocalRuntime(group = "me.shedaniel", name = "RoughlyEnoughItems-api-forge", version = reiVersion)
    modLocalRuntime(group = "dev.architectury", name = "architectury-forge", version = "8.2.89")
}
