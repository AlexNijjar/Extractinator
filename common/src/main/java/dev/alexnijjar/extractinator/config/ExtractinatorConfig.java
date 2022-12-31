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
}
