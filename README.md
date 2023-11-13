<div align="center">

## Extractinator

[![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/extractinator)
<hr>

### 📖About 📖

<hr>
</div>

This mod adds a new machine, the Extractinator, which converts your throwaway blocks, such as gravel and cobblestone,
into valuable resources. It's based on Terraria's [Silt Extractinator](https://terraria.fandom.com/wiki/Extractinator),
incorporating silt and slush ore, along with some
new features, such as full automation, support for additional blocks and extensive support for other mods.

## Default Recipe 

![Recipe](https://i.imgur.com/cx5XuCZ.png)

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
    modImplementation "tech.alexnijjar.extractinator:extractinator-common-$rootProject.minecraft_version:$rootProject.extractinator_version"
}
```

Fabric

```groovy
dependencies {
    modImplementation "tech.alexnijjar.extractinator:extractinator-fabric-$rootProject.minecraft_version:$rootProject.extractinator_version"
}
```

Forge

```groovy
dependencies {
    modImplementation "tech.alexnijjar.extractinator:extractinator-forge-$rootProject.minecraft_version:$rootProject.extractinator_version"
}
```

---

<div align="center">
</div>
