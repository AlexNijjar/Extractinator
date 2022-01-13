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
                        if (TEUtils.modIsLoaded(SupportedMods.MI)) {

                            FabricLootPoolBuilder poolBuilder1 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("tin_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder2 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 2))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("lead_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.35f).build());
                            FabricLootPoolBuilder poolBuilder3 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 2))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("silver_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.35f).build());
                            FabricLootPoolBuilder poolBuilder4 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("bronze_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(9, 18)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.35f).build());
                            FabricLootPoolBuilder poolBuilder5 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("steel_ingot"))))
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
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("tungsten_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder7 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("titanium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder8 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("platinum_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder9 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("chromium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder10 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("uranium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder11 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getMiID("iridium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());

                                table.pool(poolBuilder6);
                                table.pool(poolBuilder7);
                                table.pool(poolBuilder8);
                                table.pool(poolBuilder9);
                                table.pool(poolBuilder10);
                                table.pool(poolBuilder11);
                            }

                        } else if (TEUtils.modIsLoaded(SupportedMods.TR)) {

                            FabricLootPoolBuilder poolBuilder1 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("tin_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder2 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("lead_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder3 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("silver_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder4 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("bronze_ingot"))))
                                    .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7)).build())
                                    .withCondition(RandomChanceLootCondition.builder(0.5f).build());
                            FabricLootPoolBuilder poolBuilder5 = FabricLootPoolBuilder.builder()
                                    .rolls(UniformLootNumberProvider.create(1, 3))
                                    .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("steel_ingot"))))
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
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("tungsten_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder7 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("titanium_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder8 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("platinum_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder9 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("chrome_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder10 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("advanced_alloy_ingot"))))
                                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8)).build())
                                        .withCondition(RandomChanceLootCondition.builder(0.2f).build());
                                FabricLootPoolBuilder poolBuilder11 = FabricLootPoolBuilder.builder()
                                        .rolls(ConstantLootNumberProvider.create(1))
                                        .with(ItemEntry.builder(Registry.ITEM.get(TEIdentifier.getTrID("iridium_ingot"))))
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