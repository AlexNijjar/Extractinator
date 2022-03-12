package com.github.alexnijjar.the_extractinator;

import com.github.alexnijjar.the_extractinator.config.TheExtractinatorConfig;
import com.github.alexnijjar.the_extractinator.data.LootTable;
import com.github.alexnijjar.the_extractinator.data.ResourceData;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;
import com.github.alexnijjar.the_extractinator.networking.TEC2SPackets;
import com.github.alexnijjar.the_extractinator.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TheExtractinator implements ModInitializer {

    public static final String MOD_ID = "the_extractinator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static TheExtractinatorConfig CONFIG;

    public static List<LootTable> lootTables = new ArrayList<>();
    public static List<SupportedBlock> supportedBlocks = new ArrayList<>();

    @Override
    public void onInitialize() {

        // Register config.
        AutoConfig.register(TheExtractinatorConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(TheExtractinatorConfig.class).getConfig();

        // Registry.
        TEBlockEntities.register();
        TEBlocks.register();
        TEItems.register();
        TEOres.register();
        TEStats.register();

        // Data-driven stuff.
        ResourceData.register();

        // Networking.
        TEC2SPackets.register();

        LOGGER.info("The Extractinator initialized!");
    }
}