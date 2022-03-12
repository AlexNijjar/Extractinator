package com.github.alexnijjar.the_extractinator.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "the_extractinator")
@Config.Gui.Background("the_extractinator:textures/block/silt.png")
public class TheExtractinatorConfig implements ConfigData {

    @ConfigEntry.Category("Extractinator")
    @ConfigEntry.Gui.TransitiveObject
    public ExtractinatorConfig extractinatorConfig = new ExtractinatorConfig();

    @ConfigEntry.Category("World")
    @ConfigEntry.Gui.TransitiveObject
    public WorldConfig worldConfig = new WorldConfig();

}