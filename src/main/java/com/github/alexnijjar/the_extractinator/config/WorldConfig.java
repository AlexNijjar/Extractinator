package com.github.alexnijjar.the_extractinator.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "world")
public class WorldConfig implements ConfigData {

    public boolean generateSiltOre_v1 = true;
    public int siltVeinSize_v1 = 12;
    public int siltVeinsPerChunk_v1 = 7;
    public int siltMaxSpawnHeight_v1 = 24;

    public boolean generateCabins_v1 = true;
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int cabinSpacing_v1 = 9;
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int cabinSeparation_v1 = 5;

    @ConfigEntry.BoundedDiscrete(min = -64, max = 256)
    public int cabinMinHeight_v1 = 0;
    @ConfigEntry.BoundedDiscrete(min = -32, max = 288)
    public int cabinMaxHeight_v1 = 38;

    @ConfigEntry.BoundedDiscrete(min = -64, max = 256)
    public int deepslateCabinMinHeight_v1 = -48;
    @ConfigEntry.BoundedDiscrete(min = -32, max = 288)
    public int deepslateCabinMaxHeight_v1 = -8;

    @ConfigEntry.BoundedDiscrete(min = -64, max = 256)
    public int oceanCabinMinHeight_v1 = 28;
    @ConfigEntry.BoundedDiscrete(min = -32, max = 288)
    public int oceanCabinMaxHeight_v1 = 44;

    @ConfigEntry.Gui.Tooltip(count = 2)
    public int intactCabinWeight_v1 = 1;
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int brokenCabinWeight_v1 = 2;
    @ConfigEntry.Gui.Tooltip(count = 3)
    @ConfigEntry.Gui.RequiresRestart
    public boolean moddedLootInChests_v1 = true;

    public void validatePostLoad() {

        if (siltVeinSize_v1 < 0) siltVeinSize_v1 = 0;
        if (siltVeinsPerChunk_v1 < 0) siltVeinsPerChunk_v1 = 0;

        if (cabinSpacing_v1 == cabinSeparation_v1) cabinSpacing_v1 = cabinSeparation_v1++;
        if (cabinMinHeight_v1 <= cabinMaxHeight_v1) cabinMinHeight_v1 = cabinMaxHeight_v1++;
        if (deepslateCabinMinHeight_v1 <= deepslateCabinMaxHeight_v1) cabinMinHeight_v1 = cabinMaxHeight_v1++;
        if (oceanCabinMinHeight_v1 <= oceanCabinMaxHeight_v1) oceanCabinMinHeight_v1 = oceanCabinMaxHeight_v1++;
    }
}