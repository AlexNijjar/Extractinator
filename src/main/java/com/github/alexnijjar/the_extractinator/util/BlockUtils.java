package com.github.alexnijjar.the_extractinator.util;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.SupportedBlocksConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

import java.util.List;

public class BlockUtils {

    // This places a block above silently, preventing the block from playing a sound twice.
    public static void placeBlockSilently(World world, BlockPos pos, Block block) {
        WorldChunk chunk = world.getWorldChunk(pos);
        chunk.setBlockState(pos, block.getDefaultState(), false);
        world.updateNeighbors(pos, block);
    }

    public static boolean inputSupported(Item item) {

        if (item != Items.AIR) {
            List<String> supportedMods = TheExtractinator.CONFIG.extractinatorConfig.supportedMods_v2;
            for (SupportedBlocksConfig supported : TheExtractinator.CONFIG.extractinatorConfig.supportedBlocks_v3) {
                boolean isSupported = false;
                for (String mod : supportedMods) {
                    if (mod.equals(Registry.ITEM.getId(item).getNamespace())) {
                        isSupported = true;
                        break;
                    }
                }
                if (!isSupported) return false;

                Block block = Registry.BLOCK.get(new Identifier(supported.name));

                if (item.equals(block.asItem())) return true;
            }
        }

        return false;
    }
}
