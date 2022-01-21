package com.github.alexnijjar.the_extractinator.loot;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.util.SupportedMods;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LootTableModifier {

    // Adds modded items to cabin chests.
    public static void modifyCabinLoot() {

        Identifier metalWorks1 = new TEIdentifier("chests/cabin_metalworks");
        Identifier metalWorks2 = new TEIdentifier("chests/cabin_enhanced_metalworks");

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (id.getNamespace().equals(TheExtractinator.MOD_ID)) {

                if (TheExtractinator.CONFIG.worldConfig.moddedLootInChests) {
                    if (id.equals(metalWorks1) || id.equals(metalWorks2)) {
                        if (TEUtils.modLoaded(SupportedMods.MODERN_INDUSTRIALIZATION)) {

                            FabricLootPoolBuilder poolBuilder1 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("tin_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder2 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 2))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("lead_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.35f).build());
                            FabricLootPoolBuilder poolBuilder3 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 2))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("silver_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.35f).build());
                            FabricLootPoolBuilder poolBuilder4 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("bronze_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(9, 18)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.35f).build());
                            FabricLootPoolBuilder poolBuilder5 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("steel_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(6, 12)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.35f).build());

                            table.pool(poolBuilder1);
                            table.pool(poolBuilder2);
                            table.pool(poolBuilder3);
                            table.pool(poolBuilder4);
                            table.pool(poolBuilder5);

                            if (id.equals(metalWorks2)) {
                                FabricLootPoolBuilder poolBuilder6 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("tungsten_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder7 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("titanium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder8 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("platinum_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder9 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("chromium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder10 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("uranium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder11 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiId("iridium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());

                                table.pool(poolBuilder6);
                                table.pool(poolBuilder7);
                                table.pool(poolBuilder8);
                                table.pool(poolBuilder9);
                                table.pool(poolBuilder10);
                                table.pool(poolBuilder11);
                            }

                        } else if (TEUtils.modLoaded(SupportedMods.TECHREBORN)) {

                            FabricLootPoolBuilder poolBuilder1 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("tin_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder2 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("lead_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder3 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("silver_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder4 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("bronze_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder5 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("steel_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());

                            table.pool(poolBuilder1);
                            table.pool(poolBuilder2);
                            table.pool(poolBuilder3);
                            table.pool(poolBuilder4);
                            table.pool(poolBuilder5);

                            if (id.equals(metalWorks2)) {
                                FabricLootPoolBuilder poolBuilder6 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("tungsten_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder7 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("titanium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder8 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("platinum_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder9 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("chrome_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder10 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("advanced_alloy_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder11 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrId("iridium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());

                                table.pool(poolBuilder6);
                                table.pool(poolBuilder7);
                                table.pool(poolBuilder8);
                                table.pool(poolBuilder9);
                                table.pool(poolBuilder10);
                                table.pool(poolBuilder11);
                            }
                        }
                    }
                }
            }
        });
    }
}