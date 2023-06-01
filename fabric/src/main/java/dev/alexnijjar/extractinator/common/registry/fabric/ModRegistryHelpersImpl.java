package dev.alexnijjar.extractinator.common.registry.fabric;

import com.mojang.datafixers.util.Pair;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.common.registry.ModRegistryHelpers;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModRegistryHelpersImpl {
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(ModRegistryHelpers.BlockEntityFactory<E> factory, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory::create, blocks).build(null);
    }

    public static <V, T extends V> Pair<Supplier<T>, ResourceLocation> registerFull(Registry<V> registry, String id, Supplier<T> object) {
        var register = Registry.register(registry, new ResourceLocation(Extractinator.MOD_ID, id), object.get());
        return Pair.of(() -> register, new ResourceLocation(Extractinator.MOD_ID, id));
    }
}
