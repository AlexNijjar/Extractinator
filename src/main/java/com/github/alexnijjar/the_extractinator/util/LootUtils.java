package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.compat.rei.util.Tier;
import com.github.alexnijjar.the_extractinator.config.AdditionalDropsConfig;
import com.github.alexnijjar.the_extractinator.config.ExtractinatorConfig;
import com.github.alexnijjar.the_extractinator.config.SupportedBlocksConfig;
import com.github.alexnijjar.the_extractinator.registry.TEStats;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class LootUtils {

    public static final Map<String, Integer> lootWeights = ImmutableMap.of(
            TEUtils.modToModId(SupportedMods.MODERN_INDUSTRIALIZATION), 3,
            TEUtils.modToModId(SupportedMods.TECHREBORN), 2,
            TEUtils.modToModId(SupportedMods.TECHREBORN) + "/addon", 2,
            TEUtils.modToModId(SupportedMods.MINECRAFT), 2,
            TEUtils.modToModId(SupportedMods.MINECRAFT) + "/addon", 4,
            TEUtils.modToModId(SupportedMods.INDREV), 1,
            TEUtils.modToModId(SupportedMods.AE2), 1,
            TEUtils.modToModId(SupportedMods.MYTHICMETALS), 1,
            TEUtils.modToModId(SupportedMods.MYTHICMETALS) + "/addon", 1,
            TEUtils.underscoreToHyphen(TEUtils.modToModId(SupportedMods.NUMISMATIC_OVERHAUL)), 4
    );

    // Obtains loot from a loot table for the extractinator to spit out.
    public static List<ItemStack> extractMaterials(BlockState block, ServerWorld world, BlockPos pos) {

        ExtractinatorConfig extractinatorConfig = TheExtractinator.CONFIG.extractinatorConfig;

        for (SupportedBlocksConfig supportedBlocks : extractinatorConfig.supportedBlocks_v3) {

            if (supportedBlocks.tier == Tier.NONE) return new ArrayList<>();

            Block supportedBlock = Registry.BLOCK.get(new Identifier(supportedBlocks.name));

            if (block.isOf(supportedBlock)) {

                // Update player stats.
                for (ServerPlayerEntity player : PlayerLookup.tracking(world, pos)) {
                    player.increaseStat(TEStats.BLOCKS_EXTRACTINATED, 1);
                }

                // Adds a random chance not to drop anything based on the block's yield in the config.
                float yield = (float) supportedBlocks.yield / 100;
                if (Math.random() > yield) return new ArrayList<>();

                // Adds loot based on which mods are installed.
                Map<Identifier, Integer> paths = getLoot(supportedBlocks.tier);
                if (paths.isEmpty()) return new ArrayList<>();


                // Apply weight. This is only for modded scenarios where multiple supported mods are installed.
                // It balances the loot extracted, by reducing the loot gained by mods with limited, support,
                // such as Indrev and AE2, and increases the loot for larger mods.
                List<Integer> values = new ArrayList<>(paths.values());
                List<Identifier> keys = new ArrayList<>(paths.keySet());
                List<Identifier> chance = new ArrayList<>();

                for (int x = 0; x < values.size(); x++) {
                    int val = values.get(x);

                    for (int y = 0; y < val; y++) {
                        chance.add(keys.get(x));
                    }
                }

                // Obtain a random loot table from the list.
                Random random = world.random;
                int result = random.nextInt(chance.size());
                Identifier lootId = chance.get(result);


                // Create the loot table.
                MinecraftServer server = world.getServer();

                LootTable loot = server.getLootManager().getTable(lootId);
                LootContext.Builder builder = (new LootContext.Builder(world))
                        .random(world.random);
                List<ItemStack> generatedLoot = loot.generateLoot(builder.build(LootContextTypes.EMPTY));


                // Items to remove from loot tables, defined in the config.
                List<String> disabledDrops = supportedBlocks.disabledDrops;

                List<ItemStack> itemToRemove = new ArrayList<>();
                if (!disabledDrops.isEmpty() && !generatedLoot.isEmpty()) {

                    disabledDrops.forEach((drop) ->
                            generatedLoot.forEach((generated) -> {

                                Item disabledItem = TEUtils.getItem(new Identifier(drop));
                                if (generated.getItem().equals(disabledItem)) {
                                    itemToRemove.add(generated);
                                }
                            }));
                }
                generatedLoot.removeAll(itemToRemove);


                // Additional items to drop, defined in the config.
                List<AdditionalDropsConfig> additionalDrops = supportedBlocks.additionalDrops;

                if (!additionalDrops.isEmpty()) {

                    List<AdditionalDropsConfig> commonDrops = new ArrayList<>();
                    List<AdditionalDropsConfig> uncommonDrops = new ArrayList<>();
                    List<AdditionalDropsConfig> rareDrops = new ArrayList<>();
                    List<AdditionalDropsConfig> veryRareDrops = new ArrayList<>();
                    List<AdditionalDropsConfig> extremelyRareDrops = new ArrayList<>();

                    // Separate the drops by rarity.
                    for (AdditionalDropsConfig drop : additionalDrops) {
                        switch (drop.rarity) {
                            case COMMON -> commonDrops.add(drop);
                            case UNCOMMON -> uncommonDrops.add(drop);
                            case RARE -> rareDrops.add(drop);
                            case VERY_RARE -> veryRareDrops.add(drop);
                            case EXTREMELY_RARE -> extremelyRareDrops.add(drop);
                        }
                    }

                    // Get one random drop for each rarity.
                    List<AdditionalDropsConfig> drops = new ArrayList<>();

                    if (!commonDrops.isEmpty()) drops.add(commonDrops.get(random.nextInt(commonDrops.size())));
                    if (!uncommonDrops.isEmpty()) drops.add(uncommonDrops.get(random.nextInt(uncommonDrops.size())));
                    if (!rareDrops.isEmpty()) drops.add(rareDrops.get(random.nextInt(rareDrops.size())));
                    if (!veryRareDrops.isEmpty()) drops.add(veryRareDrops.get(random.nextInt(veryRareDrops.size())));
                    if (!extremelyRareDrops.isEmpty()) drops.add(extremelyRareDrops.get(random.nextInt(extremelyRareDrops.size())));

                    // Then, add the drops to the generated loot.
                    for (AdditionalDropsConfig drop : drops) {
                        Identifier dropId = new Identifier(drop.name);

                        if (TEUtils.modLoaded(dropId.getNamespace()) && TEUtils.modEnabled(dropId)) {
                            if (random.nextFloat() < TEUtils.rarityToPercent(drop.rarity)) {
                                int range = random.nextInt(drop.min, drop.max + 1);
                                generatedLoot.add(new ItemStack(Registry.ITEM.get(dropId), range));
                            }
                        }
                    }
                }

                return generatedLoot;
            }
        }
        return new ArrayList<>();
    }

    // Gets the paths and weight for each supported mod.
    private static Map<Identifier, Integer> getLoot(Tier tier) {

        Map<Identifier, Integer> paths = new HashMap<>();

        for (String mod : lootWeights.keySet()) {
            // Tier one does not exist for Mythic Metals addon.
            if (mod.equals(TEUtils.modToModId(SupportedMods.MYTHICMETALS) + "/addon") && tier.equals(Tier.TIER_1))
                continue;

            if (TEUtils.validMod(mod)) {
                paths.put(new TEIdentifier("gameplay/extractinator/" + mod + "/" + TEUtils.tierToString(tier)), lootWeights.get(mod));
            }
        }

        return paths;
    }
}
