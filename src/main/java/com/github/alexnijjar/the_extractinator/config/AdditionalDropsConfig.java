package com.github.alexnijjar.the_extractinator.config;

import com.github.alexnijjar.the_extractinator.compat.rei.util.Rarity;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "additional_drops")
public class AdditionalDropsConfig {

    public String name;
    public int min;
    public int max;
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Rarity rarity;

    // Don't delete, will crash the config.
    public AdditionalDropsConfig() {
        this("minecraft:empty", 1, 1, Rarity.COMMON);
    }

    public AdditionalDropsConfig(String dropName, int min, int max, Rarity rarity) {
        this.name = dropName;
        this.min = min;
        this.max = max;
        this.rarity = rarity;
    }

    public AdditionalDropsConfig(String dropName, Rarity rarity) {
        this.name = dropName;
        this.min = 1;
        this.max = 1;
        this.rarity = rarity;
    }
}
