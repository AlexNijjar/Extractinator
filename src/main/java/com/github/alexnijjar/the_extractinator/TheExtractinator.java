package com.github.alexnijjar.the_extractinator;

import com.github.alexnijjar.the_extractinator.compat.rei.util.LootTable;
import com.github.alexnijjar.the_extractinator.config.TEConfig;
import com.github.alexnijjar.the_extractinator.loot.ExtractinatorLootTables;
import com.github.alexnijjar.the_extractinator.registry.*;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.loot.LootManager;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TheExtractinator implements ModInitializer {

    public static final String MOD_ID = "the_extractinator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Identifier REI_DISPLAY_LOOT_PACKET_ID = new TEIdentifier("rei_display_loot");
    public static TEConfig CONFIG;

    @Override
    public void onInitialize() {

        // Register config.
        AutoConfig.register(TEConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(TEConfig.class).getConfig();

        // Registry.
        TEBlocks.register();
        TEBlockEntities.register();
        TEItems.register();
        TEOres.register();
        TELootTables.register();
        TERecipes.register();
        TEStats.register();

        // REI
        // Obtains all the loot tables from "gameplay/extractinator" and sends it to players entering the server.
        // The loot is then processed and displayed in REI.
        ServerPlayConnectionEvents.JOIN.register((handler, sender, minecraftServer) -> {

            LootManager manager = minecraftServer.getLootManager();
            List<LootTable> tables = ExtractinatorLootTables.getLoot(manager);

            PacketByteBuf buf = PacketByteBufs.create();

            buf.writeCollection(tables, (buf2, loot) -> {
                buf2.writeEnumConstant(loot.tier);

                buf2.writeCollection(loot.slots, (buf3, s) -> {
                    buf3.writeIdentifier(s.item);
                    buf3.writeEnumConstant(s.rarity);

                    buf3.writeIntArray(new int[]{s.range.getMinimum(), s.range.getMaximum()});
                });

                buf2.writeString(loot.namespace);
            });

            sender.sendPacket(REI_DISPLAY_LOOT_PACKET_ID, buf);

            if (minecraftServer.isDedicated())
                LOGGER.info("Sent REI Loot info to " + handler.player.getDisplayName().asString());
        });

        LOGGER.info("The Extractinator initialized!");
    }
}