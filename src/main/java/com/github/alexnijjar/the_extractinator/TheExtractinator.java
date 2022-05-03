package com.github.alexnijjar.the_extractinator;

import java.util.ArrayList;
import java.util.List;

import com.github.alexnijjar.the_extractinator.config.TheExtractinatorConfig;
import com.github.alexnijjar.the_extractinator.data.LootTable;
import com.github.alexnijjar.the_extractinator.data.ResourceData;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;
import com.github.alexnijjar.the_extractinator.networking.ModC2SPackets;
import com.github.alexnijjar.the_extractinator.registry.ModBlockEntities;
import com.github.alexnijjar.the_extractinator.registry.ModBlocks;
import com.github.alexnijjar.the_extractinator.registry.ModItems;
import com.github.alexnijjar.the_extractinator.registry.ModOres;
import com.github.alexnijjar.the_extractinator.registry.ModStats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

public class TheExtractinator implements ModInitializer {

    public static final String MOD_ID = "the_extractinator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static TheExtractinatorConfig CONFIG;

    @Environment(EnvType.SERVER)
    public static List<LootTable> lootTables = new ArrayList<>();
    @Environment(EnvType.SERVER)
    public static List<SupportedBlock> supportedBlocks = new ArrayList<>();

    @Override
    public void onInitialize() {

        // Register config.
        AutoConfig.register(TheExtractinatorConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(TheExtractinatorConfig.class).getConfig();

        // Registry.
        ModBlockEntities.register();
        ModBlocks.register();
        ModItems.register();
        ModOres.register();
        ModStats.register();

        // Data-driven stuff.
        ResourceData.register();

        // Networking.
        ModC2SPackets.register();

        LOGGER.info("The Extractinator initialized!");
    }
}