package dev.alexnijjar.extractinator.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.blocks.ExtractinatorBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {
    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Extractinator.MOD_ID);

    public static final RegistryEntry<BlockEntityType<ExtractinatorBlockEntity>> EXTRACTINATOR = BLOCK_ENTITY_TYPES.register("extractinator", () -> ModRegistryHelpers.createBlockEntityType(ExtractinatorBlockEntity::new, ModBlocks.EXTRACTINATOR.get()));
}
