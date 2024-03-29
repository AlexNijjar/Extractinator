package tech.alexnijjar.extractinator.common.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Comment;
import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.Gradient;
import com.teamresourceful.resourcefulconfig.web.annotations.Link;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;
import tech.alexnijjar.extractinator.Extractinator;

@Config(Extractinator.MOD_ID)
@WebInfo(
    title = "Extractinator",
    description = "Convert blocks into resources.",

    icon = "cog",
    gradient = @Gradient(value = "45deg", first = "#c2e59c", second = "#64b3f4"),

    links = {
        @Link(value = "https://github.com/alexnijjar/Extractinator", icon = "github", title = "GitHub"),
        @Link(value = "https://www.curseforge.com/minecraft/mc-mods/extractinator", icon = "curseforge", title = "CurseForge"),
        @Link(value = "https://modrinth.com/mod/extractinator", icon = "modrinth", title = "Modrinth"),
    }
)
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

    @ConfigEntry(
        id = "silent",
        type = EntryType.BOOLEAN,
        translation = "text.resourcefulconfig.extractinator.option.silent"
    )
    @Comment(value = "If the extractinator should be silent", translation = "text.resourcefulconfig.extractinator.option.silent.tooltip")
    public static boolean silent = false;

    @ConfigEntry(
        id = "worldgen",
        type = EntryType.BOOLEAN,
        translation = "text.resourcefulconfig.extractinator.option.worldgen"
    )
    @Comment(value = "[Fabric Only] Generates Silt and Slush ores", translation = "text.resourcefulconfig.extractinator.option.worldgen.tooltip")
    public static boolean worldgen = true;
}
