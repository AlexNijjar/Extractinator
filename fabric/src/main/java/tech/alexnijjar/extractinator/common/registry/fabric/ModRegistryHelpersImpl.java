package tech.alexnijjar.extractinator.common.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tech.alexnijjar.extractinator.common.registry.ModRegistryHelpers;

public class ModRegistryHelpersImpl {
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(ModRegistryHelpers.BlockEntityFactory<E> factory, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory::create, blocks).build(null);
    }
}
