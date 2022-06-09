package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.data.SupportedBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

public class BlockUtils {

    // This places a block above silently, preventing the block from playing a sound twice.
    public static void placeBlockSilently(World world, BlockPos pos, Block block) {
        WorldChunk chunk = world.getWorldChunk(pos);
        chunk.setBlockState(pos, block.getDefaultState(), false);
        world.updateNeighbors(pos, block);
    }

    // Checks if the input block is one of the supported blocks.
    public static boolean inputSupported(Item item) {

        if (item != Items.AIR) {
            for (SupportedBlock supported : TheExtractinator.supportedBlocks) {
                Block block = Registry.BLOCK.get(supported.id);

                if (item.equals(block.asItem())) {
                    return true;
                }
            }
        }

        return false;
    }
}
