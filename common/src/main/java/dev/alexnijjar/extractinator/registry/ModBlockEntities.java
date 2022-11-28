package dev.alexnijjar.extractinator.registry;

import dev.alexnijjar.extractinator.blocks.ExtractinatorBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final Supplier<BlockEntityType<ExtractinatorBlockEntity>> EXTRACTINATOR = register("extractinator", () -> RegistryHelpers.createBlockEntityType(ExtractinatorBlockEntity::new, ModBlocks.EXTRACTINATOR.get()));

    private static <T extends BlockEntityType<B>, B extends BlockEntity> Supplier<T> register(String id, Supplier<T> object) {
        return RegistryHelpers.register(Registry.BLOCK_ENTITY_TYPE, id, object);
    }

    public static void init() {
    }
}
