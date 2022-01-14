package com.github.alexnijjar.the_extractinator.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "supported_mods")
public class SupportedModsConfig implements ConfigData {

    @ConfigEntry.Gui.RequiresRestart
    public boolean minecraft_support = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean modern_industrialization_support = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean techreborn_support = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean indrev_support = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean ae2_support = true;
}