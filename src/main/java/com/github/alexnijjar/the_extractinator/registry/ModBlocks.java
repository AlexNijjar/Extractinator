package com.github.alexnijjar.the_extractinator.registry;

import static net.minecraft.block.AbstractBlock.Settings.copy;

import com.github.alexnijjar.the_extractinator.blocks.ExtractinatorBlock;
import com.github.alexnijjar.the_extractinator.util.ModIdentifier;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public final class ModBlocks {

    public static final Block EXTRACTINATOR_BLOCK = new ExtractinatorBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE).requiresTool());
    public static final Block SILT = new FallingBlock(copy(Blocks.GRAVEL).hardness(1.8f).requiresTool());
    public static final Block SLUSH = new FallingBlock(copy(Blocks.SNOW_BLOCK).hardness(1.8f).requiresTool());

    public static void register() {
        Registry.register(Registry.BLOCK, new ModIdentifier("extractinator_block"), EXTRACTINATOR_BLOCK);
        Registry.register(Registry.BLOCK, new ModIdentifier("silt"), SILT);
        Registry.register(Registry.BLOCK, new ModIdentifier("slush"), SLUSH);
    }
}
