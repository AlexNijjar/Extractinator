package dev.alexnijjar.extractinator.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.common.block.ExtractinatorBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(Registry.BLOCK, Extractinator.MOD_ID);

    public static final RegistryEntry<Block> EXTRACTINATOR = BLOCKS.register("extractinator", () -> new ExtractinatorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryEntry<Block> SILT = BLOCKS.register("silt", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL).strength(1.8f)));
    public static final RegistryEntry<Block> SLUSH = BLOCKS.register("slush", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).strength(1.8f)));
}
