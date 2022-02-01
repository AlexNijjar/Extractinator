package com.github.alexnijjar.the_extractinator.config;

import com.github.alexnijjar.the_extractinator.recipe.ExtractinatorRecipe;
import com.github.alexnijjar.the_extractinator.util.SupportedMods;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@Config(name = "extractinator")
public class ExtractinatorConfig implements ConfigData {

    @ConfigEntry.Gui.RequiresRestart
    public float commonItemChance_v1 = 100;

    @ConfigEntry.Gui.RequiresRestart
    public float uncommonItemChance_v1 = 50;

    @ConfigEntry.Gui.RequiresRestart
    public float rareItemChance_v1 = 25;

    @ConfigEntry.Gui.RequiresRestart
    public float veryRareItemChance_v1 = 10;

    @ConfigEntry.Gui.RequiresRestart
    public float extremelyRareItemChance_v1 = 2;

    @ConfigEntry.Gui.Tooltip(count = 5)
    public int inputCooldown_v1 = 8;

    @ConfigEntry.Gui.Tooltip(count = 2)
    public float outputLootMultiplier_v1 = 1.0f;

    @ConfigEntry.Gui.Tooltip(count = 5)
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    @ConfigEntry.Gui.RequiresRestart
    public ExtractinatorRecipe extractinatorRecipe_v2 = ExtractinatorRecipe.NONE;

    @ConfigEntry.Gui.Tooltip
    public List<String> supportedMods_v1 = TEUtils.modsToList();

    // This breaks the Jankson Serializer for some reason. Solution is to use Toml serializer.
    @ConfigEntry.Gui.Tooltip(count = 6)
    public List<SupportedBlocksConfig> supportedBlocks_v2 = SupportedBlocks.supportedBlocks;

    // Sets the default recipe to something when Subterrestrial is not installed.
    public ExtractinatorConfig() {
        if (!TEUtils.modLoaded("subterrestrial")) {
            if (TEUtils.modLoaded(SupportedMods.MODERN_INDUSTRIALIZATION)) {
                extractinatorRecipe_v2 = ExtractinatorRecipe.MODERN_INDUSTRIALIZATION;
            } else if (TEUtils.modLoaded(SupportedMods.TECHREBORN)) {
                extractinatorRecipe_v2 = ExtractinatorRecipe.TECH_REBORN;
            } else {
                extractinatorRecipe_v2 = ExtractinatorRecipe.MINECRAFT;
            }
        }
    }

    @Override
    public void validatePostLoad() {

        commonItemChance_v1 = MathHelper.clamp(commonItemChance_v1, 0, 100);
        uncommonItemChance_v1 = MathHelper.clamp(commonItemChance_v1, 0, 100);
        rareItemChance_v1 = MathHelper.clamp(commonItemChance_v1, 0, 100);
        veryRareItemChance_v1 = MathHelper.clamp(commonItemChance_v1, 0, 100);
        extremelyRareItemChance_v1 = MathHelper.clamp(commonItemChance_v1, 0, 100);
        if (inputCooldown_v1 < 1) {
            inputCooldown_v1 = 1;
        }
    }
}
