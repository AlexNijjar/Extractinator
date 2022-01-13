package com.github.alexnijjar.the_extractinator.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "supported_mods")
public class SupportedModsConfig implements ConfigData {

    public boolean minecraft_support = true;
    public boolean modern_industrialization_support = true;
    public boolean techreborn_support = true;
    public boolean indrev_support = true;
    public boolean ae2_support = true;
}