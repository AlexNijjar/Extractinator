package dev.alexnijjar.extractinator.registry;

import dev.alexnijjar.extractinator.blocks.ExtractinatorBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocks {

    public static final Supplier<Block> EXTRACTINATOR = register("extractinator", () -> new ExtractinatorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final Supplier<Block> SILT = register("silt", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL).strength(1.8f)));
    public static final Supplier<Block> SLUSH = register("slush", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).strength(1.8f)));

    private static <T extends Block> Supplier<T> register(String id, Supplier<T> block) {
        return RegistryHelpers.register(Registry.BLOCK, id, block);
    }

    public static void init() {

    }
}
