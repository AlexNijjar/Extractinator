package com.github.alexnijjar.the_extractinator.config;

import com.github.alexnijjar.the_extractinator.util.ModUtils;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.math.MathHelper;

@Config(name = "extractinator")
public class ExtractinatorConfig implements ConfigData {

    @ConfigEntry.Gui.RequiresRestart
    public float commonItemChance = 100;
    @ConfigEntry.Gui.RequiresRestart
    public float uncommonItemChance = 50;
    @ConfigEntry.Gui.RequiresRestart
    public float rareItemChance = 25;
    @ConfigEntry.Gui.RequiresRestart
    public float veryRareItemChance = 10;
    @ConfigEntry.Gui.RequiresRestart
    public float extremelyRareItemChance = 2;

    @ConfigEntry.Gui.Tooltip(count = 5)
    public int inputCooldown = 8;

    @ConfigEntry.Gui.Tooltip(count = 2)
    public float outputLootMultiplier = 1.0f;

    @ConfigEntry.Gui.Tooltip(count = 3)
    public boolean enableReiPercent = false;

    @ConfigEntry.Gui.Tooltip(count = 2)
    public int maximumUsages = 0;

    @ConfigEntry.Gui.Tooltip(count = 2)
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ExtractinatorRecipe extractinatorRecipe = ExtractinatorRecipe.NONE;

    // Sets the default recipe to something when Subterrestrial is not installed.
    public ExtractinatorConfig() {
        if (!ModUtils.modLoaded("subterrestrial")) {
            if (ModUtils.modLoaded("modern_industrialization")) {
                extractinatorRecipe = ExtractinatorRecipe.MODERN_INDUSTRIALIZATION;
            } else if (ModUtils.modLoaded("techreborn")) {
                extractinatorRecipe = ExtractinatorRecipe.TECH_REBORN;
            } else {
                extractinatorRecipe = ExtractinatorRecipe.MINECRAFT;
            }
        }
    }

    @Override
    public void validatePostLoad() {

        commonItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        uncommonItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        rareItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        veryRareItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        extremelyRareItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        if (inputCooldown < 1) {
            inputCooldown = 1;
        }
    }
}