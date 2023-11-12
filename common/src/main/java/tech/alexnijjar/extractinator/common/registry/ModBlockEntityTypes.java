package tech.alexnijjar.extractinator.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tech.alexnijjar.extractinator.Extractinator;
import tech.alexnijjar.extractinator.common.block.ExtractinatorBlockEntity;

public class ModBlockEntityTypes {
    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Extractinator.MOD_ID);

    public static final RegistryEntry<BlockEntityType<ExtractinatorBlockEntity>> EXTRACTINATOR = BLOCK_ENTITY_TYPES.register("extractinator", () -> ModRegistryHelpers.createBlockEntityType(ExtractinatorBlockEntity::new, ModBlocks.EXTRACTINATOR.get()));
}
