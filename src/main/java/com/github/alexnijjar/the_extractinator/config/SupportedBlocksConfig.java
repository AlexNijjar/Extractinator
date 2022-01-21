package com.github.alexnijjar.the_extractinator.config;

import com.github.alexnijjar.the_extractinator.compat.rei.Tier;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;
import java.util.List;

@Config(name = "supported_blocks")
public class SupportedBlocksConfig {

    public String name;
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Tier tier;
    public int yield;
    public List<AdditionalDropsConfig> additionalDrops;
    public List<String> disabledDrops;

    // Don't delete, will crash the config.
    public SupportedBlocksConfig() {
        this("minecraft:empty", Tier.TIER_5, 100, new ArrayList<>(), new ArrayList<>());
    }

    public SupportedBlocksConfig(String name, Tier tier, int yield, List<AdditionalDropsConfig> additionalDrops, List<String> disabledDrops) {
        this.name = name;
        this.tier = tier;
        this.yield = yield;
        this.additionalDrops = additionalDrops;
        this.disabledDrops = disabledDrops;
    }

    public SupportedBlocksConfig(String name, Tier tier, int yield, List<AdditionalDropsConfig> additionalDrops) {
        this.name = name;
        this.tier = tier;
        this.yield = yield;
        this.additionalDrops = additionalDrops;
        this.disabledDrops = new ArrayList<>();
    }

    public SupportedBlocksConfig(String name, Tier tier, int yield) {
        this.name = name;
        this.tier = tier;
        this.yield = yield;
        this.additionalDrops = new ArrayList<>();
        this.disabledDrops = new ArrayList<>();
    }
}
