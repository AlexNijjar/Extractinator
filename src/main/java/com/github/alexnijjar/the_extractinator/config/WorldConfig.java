package com.github.alexnijjar.the_extractinator.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "world")
public class WorldConfig implements ConfigData {

    public boolean generateSiltOre = true;
    public int siltVeinSize = 12;
    public int siltVeinsPerChunk = 8;
    public int siltMaxSpawnHeight = 24;

    public boolean generateCabins = true;
    @ConfigEntry.Gui.Tooltip
    public int cabinSpacing = 7;
    @ConfigEntry.Gui.Tooltip
    public int cabinSeparation = 4;

    @ConfigEntry.BoundedDiscrete(min = -64, max = 256)
    public int cabinMinHeight = 0;
    @ConfigEntry.BoundedDiscrete(min = -32, max = 288)
    public int cabinMaxHeight = 48;

    @ConfigEntry.BoundedDiscrete(min = -64, max = 256)
    public int deepslateCabinMinHeight = -48;
    @ConfigEntry.BoundedDiscrete(min = -32, max = 288)
    public int deepslateCabinMaxHeight = -1;

    @ConfigEntry.Gui.Tooltip(count = 2)
    public int intactCabinWeight = 1;
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int brokenCabinWeight = 2;
    @ConfigEntry.Gui.Tooltip(count = 2)
    @ConfigEntry.Gui.RequiresRestart
    public boolean moddedLootInChests = true;

    public void validatePostLoad() {

        if (siltVeinSize < 0) siltVeinSize = 0;
        if (siltVeinsPerChunk < 0) siltVeinsPerChunk = 0;

        if (cabinSpacing == cabinSeparation) cabinSpacing = cabinSeparation + 1;
        if (cabinMinHeight <= cabinMaxHeight) cabinMinHeight = cabinMaxHeight + 1;
        if (deepslateCabinMinHeight <= deepslateCabinMaxHeight) cabinMinHeight = cabinMaxHeight + 1;
    }
}