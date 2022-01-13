package com.github.alexnijjar.the_extractinator.util.output;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.AdditionalDropsConfig;
import com.github.alexnijjar.the_extractinator.config.SupportedBlocksConfig;
import com.github.alexnijjar.the_extractinator.config.SupportedModsConfig;
import com.github.alexnijjar.the_extractinator.registry.TEStats;
import com.github.alexnijjar.the_extractinator.util.SupportedMods;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import com.github.alexnijjar.the_extractinator.util.Tier;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ExtractinatorBlockExtraction {

    // Obtains loot from a loot table for the extractinator to spit out.
    public static void extractMaterials(BlockState block, ServerWorld world, BlockPos pos) {

        for (SupportedBlocksConfig supportedBlocks : TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks) {

            if (supportedBlocks.tier == Tier.NONE) return;

            Block supportedBlock = Registry.BLOCK.get(new Identifier(supportedBlocks.name));

            if (block.isOf(supportedBlock)) {

                // Update player stats.
                for (ServerPlayerEntity player : PlayerLookup.tracking(world, pos)) {
                    player.increaseStat(TEStats.BLOCKS_EXTRACTINATED, 1);
                }

                String tier = TEUtils.tierPath(supportedBlocks.tier);

                // Adds a random chance not to drop anything based on the block's yield in the config.
                float yield = (float) supportedBlocks.yield / 100;
                if (Math.random() > yield) return;

                // Adds loot based on which mods are installed.
                List<Identifier> paths = getLoot(tier);
                if (paths.isEmpty()) return;

                // Obtain a random loot table from the list.
                Random random = world.random;
                int result = random.nextInt(paths.size());
                Identifier lootID = paths.get(result);

                // Create the loot table.
                MinecraftServer server = world.getServer();

                LootTable loot = server.getLootManager().getTable(lootID);
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
                    for (AdditionalDropsConfig drop : additionalDrops) {

                        if (random.nextFloat() < TEUtils.rarityValue(drop.rarity)) {
                            Identifier dropID = new Identifier(drop.name);
                            int range = random.nextInt(drop.min, drop.max + 1);
                            generatedLoot.add(new ItemStack(Registry.ITEM.get(dropID), range));
                            break;
                        }
                    }
                }

                // Spawn loot above the extractinator.
                for (ItemStack itemStack : generatedLoot) {

                    ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, itemStack);
                    itemEntity.setVelocity(itemEntity.getVelocity().multiply(1.5f));
                    itemEntity.setToDefaultPickupDelay();
                    world.spawnEntity(itemEntity);
                }
            }
        }
    }

    private static List<Identifier> getLoot(String tier) {

        List<Identifier> paths = new ArrayList<>();

        SupportedModsConfig support = TheExtractinator.CONFIG.extractinatorConfig.supportedMods;

        if (TEUtils.modIsLoaded(SupportedMods.MI) && support.modern_industrialization_support) {

            // Just Modern Industrialization.
            paths.add(new TEIdentifier("gameplay/extractinator/modern_industrialization/" + tier));

            // Modern Industrialization and Tech Reborn.
            if (TEUtils.modIsLoaded(SupportedMods.TR) && support.techreborn_support)
                paths.add(new TEIdentifier("gameplay/extractinator/techreborn/addon/" + tier));

            if (support.minecraft_support)
                paths.add(new TEIdentifier("gameplay/extractinator/minecraft/addon/" + tier));
        }

        // Just Tech Reborn.
        else if (TEUtils.modIsLoaded(SupportedMods.TR) && support.techreborn_support) {
            paths.add(new TEIdentifier("gameplay/extractinator/techreborn/" + tier));
            if (support.minecraft_support)
                paths.add(new TEIdentifier("gameplay/extractinator/minecraft/addon/" + tier));
        }

        // If MI and TR are not installed, add the default Minecraft loot table.
        else if (support.minecraft_support)
            paths.add(new TEIdentifier("gameplay/extractinator/minecraft/" + tier));

        // Indrev.
        if (TEUtils.modIsLoaded(SupportedMods.INDREV) && support.indrev_support)
            paths.add(new TEIdentifier("gameplay/extractinator/indrev/" + tier));

        // AE2.
        if (TEUtils.modIsLoaded(SupportedMods.AE2) && support.ae2_support)
            paths.add(new TEIdentifier("gameplay/extractinator/ae2/" + tier));

        return paths;
    }
}
