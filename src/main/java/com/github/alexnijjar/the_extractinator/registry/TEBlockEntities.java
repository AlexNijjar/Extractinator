package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.blocks.entity.ExtractinatorBlockEntity;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public final class TEBlockEntities {

    public static BlockEntityType<ExtractinatorBlockEntity> EXTRACTINATOR_BLOCK_ENTITY;

    public static void register() {
        EXTRACTINATOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new TEIdentifier("extractinator_entity"), FabricBlockEntityTypeBuilder.create(ExtractinatorBlockEntity::new, TEBlocks.EXTRACTINATOR_BLOCK).build(null));
    }
}
