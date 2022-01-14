package com.github.alexnijjar.the_extractinator.util.output;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.compat.rei.LootSlot;
import com.github.alexnijjar.the_extractinator.compat.rei.LootTable;
import com.github.alexnijjar.the_extractinator.config.AdditionalDropsConfig;
import com.github.alexnijjar.the_extractinator.config.SupportedBlocksConfig;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Range;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
    Loot spreadsheet:
    https://docs.google.com/spreadsheets/d/1fMDkW5NF3i8aUo75QgbrrI62U_VafD-4HfM1QJkx2ZI/edit?usp=sharing
*/
public final class ExtractinatorBlockOutput {

    public static List<LootSlot> getOutput(Identifier id, List<LootTable> tables) {

        List<LootSlot> loot = new ArrayList<>();

        List<SupportedBlocksConfig> supportedBlocks = TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks;

        List<AdditionalDropsConfig> additionalDrops = new ArrayList<>();
        List<String> disabledDrops = new ArrayList<>();
        SupportedBlocksConfig blockConfig = null;

        for (SupportedBlocksConfig supportedBlock : supportedBlocks) {
            if (id.equals(new Identifier(supportedBlock.name))) {
                blockConfig = supportedBlock;
                additionalDrops = supportedBlock.additionalDrops;
                disabledDrops = supportedBlock.disabledDrops;
                break;
            }
        }

        if (tables == null) {
            TheExtractinator.LOGGER.error("REI Loot tables for The Extractinator not found.");
            return loot;
        }

        if (blockConfig != null) {

            for (LootTable table : tables) {

                if (table.tier.equals(blockConfig.tier) && TEUtils.validLootTableMod(table)) {

                    loot.addAll(table.slots);
                }
            }
        }
        // Remove disabled drops from the list.
        for (String drop : disabledDrops) {
            loot.removeIf(item -> item.item.equals(new Identifier(drop)));
        }

        // Add additional loot.
        if (additionalDrops != null) {
            for (AdditionalDropsConfig additional : additionalDrops) {
                if (FabricLoader.getInstance().isModLoaded(new Identifier(additional.name).getNamespace()))
                    loot.add(new LootSlot(new Identifier(additional.name), Range.between(additional.min, additional.max), additional.rarity));
            }
        }

        // Sort alphabetically.
        loot.sort(Comparator.comparing(o -> o.item.getPath()));

        // Then sort by rarity.
        loot.sort(Comparator.comparing(o -> o.rarity.ordinal()));

        return loot;
    }
}