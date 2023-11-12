package tech.alexnijjar.extractinator.common.registry.forge;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tech.alexnijjar.extractinator.common.registry.ModRegistryHelpers;

public class ModRegistryHelpersImpl {
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(ModRegistryHelpers.BlockEntityFactory<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::create, blocks).build(null);
    }
}
