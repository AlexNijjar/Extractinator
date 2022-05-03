package com.github.alexnijjar.the_extractinator.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "world")
public class WorldConfig implements ConfigData {

    public boolean generateSiltOre_v1 = true;
    public int siltVeinSize_v2 = 13;
    public int siltVeinsPerChunk_v2 = 9;
    public int siltMinSpawnHeight_v1 = -64;
    public int siltMaxSpawnHeight_v1 = 24;

    public boolean generateSlushOre_v1 = true;
    public int slushVeinSize_v1 = 13;
    public int slushVeinsPerChunk_v1 = 10;
    public int slushMinSpawnHeight_v1 = 25;
    public int slushMaxSpawnHeight_v1 = 48;

    public void validatePostLoad() {

        if (siltVeinSize_v2 < 0) {
            siltVeinSize_v2 = 0;
        }
        if (siltVeinsPerChunk_v2 < 0) {
            siltVeinsPerChunk_v2 = 0;
        }
    }
}