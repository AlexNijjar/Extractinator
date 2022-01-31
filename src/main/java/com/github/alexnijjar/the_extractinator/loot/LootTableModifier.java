package com.github.alexnijjar.the_extractinator.loot;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.util.SupportedMods;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.Range;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LootTableModifier {

    public static final Map<String, List<LootData>> loot = ImmutableMap.of(
            "metalworks_loot_mi", Arrays.asList(
                    new LootData(miId("tin_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(miId("lead_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(miId("silver_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(miId("bronze_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(miId("steel_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f)
            ),
            "metalworks_loot_tr", Arrays.asList(
                    new LootData(trId("tin_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(trId("lead_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(trId("silver_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(trId("bronze_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f),
                    new LootData(trId("steel_ingot"), Range.between(3, 7), Range.between(2, 5), 0.5f)
            ),
            "enhanced_metalworks_loot_mi", Arrays.asList(
                    new LootData(miId("tungsten_ingot"), Range.between(2, 4), Range.between(1, 2), 0.25f),
                    new LootData(miId("titanium_ingot"), Range.between(1, 2), Range.between(1, 2), 0.25f),
                    new LootData(miId("platinum_ingot"), Range.between(1, 2), Range.between(1, 1), 0.25f),
                    new LootData(miId("chromium_ingot"), Range.between(1, 3), Range.between(1, 2), 0.25f),
                    new LootData(miId("uranium_ingot"), Range.between(5, 10), Range.between(1, 2), 0.25f),
                    new LootData(miId("iridium_ingot"), Range.between(1, 2), Range.between(1, 1), 0.2f)
            ),
            "enhanced_metalworks_loot_tr", Arrays.asList(
                    new LootData(trId("tungsten_ingot"), Range.between(2, 4), Range.between(1, 2), 0.25f),
                    new LootData(trId("titanium_ingot"), Range.between(1, 2), Range.between(1, 2), 0.25f),
                    new LootData(trId("platinum_ingot"), Range.between(1, 2), Range.between(1, 2), 0.25f),
                    new LootData(trId("chromium_ingot"), Range.between(2, 3), Range.between(1, 2), 0.25f),
                    new LootData(trId("advanced_alloy_ingot"), Range.between(6, 12), Range.between(1, 2), 0.25f),
                    new LootData(trId("iridium_ingot"), Range.between(1, 2), Range.between(1, 1), 0.2f)
            ),
            "kitchen_loot_crop", Arrays.asList(
                    new LootData(crId("potato_chips"), Range.between(1, 3), Range.between(1, 2), 0.2f),
                    new LootData(crId("baked_beans"), Range.between(1, 3), Range.between(1, 2), 0.2f),
                    new LootData(crId("trail_mix"), Range.between(1, 3), Range.between(1, 2), 0.2f),
                    new LootData(crId("protein_bar"), Range.between(1, 2), Range.between(1, 2), 0.2f),
                    new LootData(crId("food_press"), Range.between(1, 1), Range.between(1, 1), 0.1f),
                    new LootData(crId("frying_pan"), Range.between(1, 1), Range.between(1, 1), 0.1f)
            ),
            "cabin_bedroom_aa", Arrays.asList(
                    new LootData(aaId("glow_stick"), Range.between(3, 6), Range.between(2, 6), 0.9f),
                    new LootData(aaId("rope"), Range.between(2, 4), Range.between(1, 4), 0.25f)
            ));

    static final Identifier metalworks = new TEIdentifier("chests/cabin_metalworks");
    static final Identifier enhancedMetalworks = new TEIdentifier("chests/cabin_enhanced_metalworks");
    static final Identifier kitchen = new TEIdentifier("chests/cabin_kitchen");
    static final Identifier bedroom = new TEIdentifier("chests/cabin_bedroom");

    static Identifier miId(String path) {
        return new Identifier(TEUtils.modToModId(SupportedMods.MODERN_INDUSTRIALIZATION), path);
    }

    static Identifier trId(String path) {
        return new Identifier(TEUtils.modToModId(SupportedMods.TECHREBORN), path);
    }

    static Identifier crId(String path) {
        return new Identifier("croptopia", path);
    }

    static Identifier aaId(String path) {
        return new Identifier("additionaladditions", path);
    }

    static FabricLootPoolBuilder buildPool(LootData data) {
        return FabricLootPoolBuilder.builder()
                .rolls(UniformLootNumberProvider.create(data.amount.getMinimum(), data.amount.getMaximum()))
                .with(ItemEntry.builder(Registry.ITEM.get(data.id)))
                .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(data.slots.getMinimum(), data.slots.getMaximum())).build())
                .withCondition(RandomChanceLootCondition.builder(data.chance).build());
    }

    // Adds modded loot to cabin chests.
    public static void modifyCabinLoot() {

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {

            if (id.getNamespace().equals(TheExtractinator.MOD_ID)) {
                if (TheExtractinator.CONFIG.worldConfig.moddedLootInChests_v1) {
                    if (id.equals(LootTableModifier.metalworks) || id.equals(LootTableModifier.enhancedMetalworks)) {
                        if (TEUtils.modLoaded(SupportedMods.MODERN_INDUSTRIALIZATION)) {

                            LootTableModifier.loot.get("metalworks_loot_mi").forEach((data) -> table.pool(buildPool(data)));

                            if (id.equals(LootTableModifier.enhancedMetalworks)) {
                                LootTableModifier.loot.get("enhanced_metalworks_loot_mi").forEach((data) -> table.pool(buildPool(data)));
                            }
                        } else if (TEUtils.modLoaded(SupportedMods.TECHREBORN)) {

                            LootTableModifier.loot.get("metalworks_loot_tr").forEach((data) -> table.pool(buildPool(data)));

                            if (id.equals(LootTableModifier.enhancedMetalworks)) {
                                LootTableModifier.loot.get("enhanced_metalworks_loot_tr").forEach((data) -> table.pool(buildPool(data)));
                            }
                        }
                    }
                    if (id.equals(LootTableModifier.kitchen)) {
                        if (TEUtils.modLoaded("croptopia"))
                            LootTableModifier.loot.get("kitchen_loot_crop").forEach((data) -> table.pool(buildPool(data)));
                    }
                    if (id.equals(LootTableModifier.bedroom)) {
                        if (TEUtils.modLoaded("additionaladditions"))
                            LootTableModifier.loot.get("cabin_bedroom_aa").forEach((data) -> table.pool(buildPool(data)));
                    }
                }
            }
        });
    }
}