package dev.alexnijjar.extractinator.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;

@Config("extractinator")
public final class ExtractinatorConfig {

    @ConfigEntry(
            id = "extractTicks",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.extractTicks"
    )
    public static int extractTicks = 8;

    @ConfigEntry(
            id = "lootMultiplier",
            type = EntryType.DOUBLE,
            translation = "text.resourcefulconfig.extractinator.option.lootMultiplier"
    )
    public static double lootMultiplier = 1;

    @ConfigEntry(
            id = "extractinatorDurability",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.extractinatorDurability"
    )
    public static int extractinatorDurability = -1;

    @ConfigEntry(
            id = "siltVeinSize",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.siltVeinSize"
    )
    public static int siltVeinSize = 13;

    @ConfigEntry(
            id = "siltVeinsPerChunk",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.siltVeinsPerChunk"
    )
    public static int siltVeinsPerChunk = 9;

    @ConfigEntry(
            id = "siltMinHeight",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.siltMinHeight"
    )
    public static int siltMinHeight = -64;

    @ConfigEntry(
            id = "siltMaxHeight",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.siltMaxHeight"
    )
    public static int siltMaxHeight = 24;

    @ConfigEntry(
            id = "slushVeinSize",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.slushVeinSize"
    )
    public static int slushVeinSize = 13;

    @ConfigEntry(
            id = "slushVeinsPerChunk",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.slushVeinsPerChunk"
    )
    public static int slushVeinsPerChunk = 8;

    @ConfigEntry(
            id = "slushMinHeight",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.slushMinHeight"
    )
    public static int slushMinHeight = 25;

    @ConfigEntry(
            id = "slushMaxHeight",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.slushMaxHeight"
    )
    public static int slushMaxHeight = 48;
}
