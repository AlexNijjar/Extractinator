package dev.alexnijjar.extractinator.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.blocks.ExtractinatorBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocks {
    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, Extractinator.MOD_ID);

    public static final Supplier<Block> EXTRACTINATOR = BLOCKS.register("extractinator", () -> new ExtractinatorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final Supplier<Block> SILT = BLOCKS.register("silt", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL).strength(1.8f)));
    public static final Supplier<Block> SLUSH = BLOCKS.register("slush", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).strength(1.8f)));
}
