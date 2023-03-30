<div align="center">

## Extractinator

[![Requires Resourceful Lib](https://cdn.discordapp.com/attachments/1073717602880327761/1073717942014972034/RLib_vector.svg)](http://modrinth.com/mod/resourceful-lib)
[![Requires Resourceful Config](https://cdn.discordapp.com/attachments/1073717602880327761/1073717981118480535/RConfig_vector.svg)](http://modrinth.com/mod/resourceful-config)
[![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/extractinator)
[![CurseForge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/curseforge_vector.svg)](https://www.curseforge.com/minecraft/mc-mods/extractinator)
<hr>

### 📖About 📖

<hr>
</div>

This mod adds a new machine, the Extractinator, which converts your throwaway blocks, such as gravel and cobblestone,
into valuable resources. It's based on Terraria's [Silt Extractinator](https://terraria.fandom.com/wiki/Extractinator),
incorporating silt and slush ore, along with some
new features, such as full automation, support for additional blocks and extensive support for other mods.

## Developers

To add this library to your project, do the following:

```groovy
repositories {
  maven {
    // Location of the maven that hosts Alex's and Team Resourceful's files.
    name = "Team Resourceful Maven"
    url = "https://maven.resourcefulbees.com/repository/maven-public/"
  }
}
```

In an Architectury project, you would implement it like so:

Common

```groovy
dependencies {
    modImplementation "dev.alexnijjar:extractinator-common-$rootProject.minecraft_version:$rootProject.extractinator_version"
}
```

Fabric

```groovy
dependencies {
    modImplementation "dev.alexnijjar:extractinator-fabric-$rootProject.minecraft_version:$rootProject.extractinator_version"
}
```

Forge

```groovy
dependencies {
    modImplementation "dev.alexnijjar:extractinator-forge-$rootProject.minecraft_version:$rootProject.extractinator_version"
}
```

---

<div align="center">

![Version](https://img.shields.io/maven-metadata/v?label=Extractinator%20Version&metadataUrl=https%3A%2F%2Fmaven.resourcefulbees.com%2Frepository%2Falexnijjar%2Fdev%2Falexnijjar%2Fextractinator-common-1.19.4%2Fmaven-metadata.xml)
</div>
