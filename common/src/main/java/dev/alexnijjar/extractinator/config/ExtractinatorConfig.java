package dev.alexnijjar.extractinator.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Comment;
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
    @Comment(value = "How long it takes for the extractinator to extract a single block", translation = "text.resourcefulconfig.extractinator.option.extractTicks.tooltip")
    public static int extractTicks = 8;

    @ConfigEntry(
            id = "lootMultiplier",
            type = EntryType.DOUBLE,
            translation = "text.resourcefulconfig.extractinator.option.lootMultiplier"
    )
    @Comment(value = "Multiplies the amount of loot dropped by this value", translation = "text.resourcefulconfig.extractinator.option.lootMultiplier.tooltip")
    public static double lootMultiplier = 1;

    @ConfigEntry(
            id = "extractinatorDurability",
            type = EntryType.INTEGER,
            translation = "text.resourcefulconfig.extractinator.option.extractinatorDurability"
    )
    @Comment(value = "Amount of uses before the extractinator breaks", translation = "text.resourcefulconfig.extractinator.option.extractinatorDurability.tooltip")
    public static int extractinatorDurability = -1;
}
