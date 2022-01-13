package com.github.alexnijjar.the_extractinator.config;

import com.github.alexnijjar.the_extractinator.util.ExtractinatorRecipe;
import com.github.alexnijjar.the_extractinator.util.output.SupportedBlocks;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.math.MathHelper;

import java.util.List;

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

    @ConfigEntry.Gui.Tooltip(count = 4)
    public int inputCooldown = 8;

    @ConfigEntry.Gui.Tooltip(count = 4)
    @ConfigEntry.Gui.EnumHandler(
            option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON
    )
    @ConfigEntry.Gui.RequiresRestart
    public ExtractinatorRecipe extractinatorRecipe = ExtractinatorRecipe.NONE;

    @ConfigEntry.Gui.CollapsibleObject
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.RequiresRestart
    public SupportedModsConfig supportedMods = new SupportedModsConfig();

    // This breaks the Jankson Serializer for some reason. Solution is to use Toml serializer.
    @ConfigEntry.Gui.Tooltip(count = 6)
    @ConfigEntry.Gui.RequiresRestart
    public List<SupportedBlocksConfig> supportedBlocks = SupportedBlocks.supportedBlocks;

    @Override
    public void validatePostLoad() {

        commonItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        uncommonItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        rareItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        veryRareItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        extremelyRareItemChance = MathHelper.clamp(commonItemChance, 0, 100);
        if(inputCooldown < 1) { inputCooldown = 1; }
    }
}
