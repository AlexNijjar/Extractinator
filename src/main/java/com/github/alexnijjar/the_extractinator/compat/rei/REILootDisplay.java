package com.github.alexnijjar.the_extractinator.compat.rei;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.AdditionalDropsConfig;
import com.github.alexnijjar.the_extractinator.config.SupportedBlocksConfig;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Loot spreadsheet:
// https://docs.google.com/spreadsheets/d/1fMDkW5NF3i8aUo75QgbrrI62U_VafD-4HfM1QJkx2ZI/edit?usp=sharing
public final class REILootDisplay {

    public static List<LootSlot> getOutput(Identifier id, List<LootTable> tables) {

        List<LootSlot> loot = new ArrayList<>();

        List<SupportedBlocksConfig> supportedBlocks = TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks_v2;
        SupportedBlocksConfig supportedBlock = null;

        List<AdditionalDropsConfig> additionalDrops = new ArrayList<>();
        List<String> disabledDrops = new ArrayList<>();

        // Get the proper block config for the input block.
        for (SupportedBlocksConfig supported : supportedBlocks) {
            if (id.equals(new Identifier(supported.name))) {
                supportedBlock = supported;
                additionalDrops = supported.additionalDrops;
                disabledDrops = supported.disabledDrops;
                break;
            }
        }

        // Happens if the S2C packet fails to send. Probably means that something is wrong with the server.
        if (tables == null || supportedBlock == null) {
            TheExtractinator.LOGGER.error("REI loot tables not found.");
            return loot;
        }

        // Get the loot to display in REI, based on which mods are installed.
        for (LootTable table : tables) {
            if (table.tier.equals(supportedBlock.tier) && TEUtils.validMod(table.namespace)) {
                loot.addAll(table.slots);
            }
        }

        // Remove disabled drops from the list.
        for (String drop : disabledDrops) {
            loot.removeIf(item -> item.item.equals(new Identifier(drop)));
        }

        // Add additional loot.
        if (additionalDrops != null) for (AdditionalDropsConfig drop : additionalDrops) {
            Identifier dropId = new Identifier(drop.name);
            if (FabricLoader.getInstance().isModLoaded(dropId.getNamespace())) if (TEUtils.modEnabled(dropId))
                loot.add(new LootSlot(dropId, Range.between(drop.min, drop.max), drop.rarity));
        }

        // Sort alphabetically.
        loot.sort(Comparator.comparing(o -> o.item.getPath()));

        // Then sort by rarity.
        loot.sort(Comparator.comparing(o -> o.rarity.ordinal()));

        return loot;
    }
}