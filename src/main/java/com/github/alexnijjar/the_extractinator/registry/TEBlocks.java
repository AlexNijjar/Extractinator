package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.blocks.ExtractinatorBlock;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.registry.Registry;

import static net.minecraft.block.AbstractBlock.Settings.copy;

public final class TEBlocks {

    public static final Block EXTRACTINATOR_BLOCK = new ExtractinatorBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
    public static final Block SILT = new FallingBlock(copy(Blocks.GRAVEL));
    public static final Block SLUSH = new FallingBlock(copy(Blocks.SNOW_BLOCK).hardness(1));

    public static void register() {
        Registry.register(Registry.BLOCK, new TEIdentifier("extractinator_block"), EXTRACTINATOR_BLOCK);
        Registry.register(Registry.BLOCK, new TEIdentifier("silt"), SILT);
        Registry.register(Registry.BLOCK, new TEIdentifier("slush"), SLUSH);
    }
}
