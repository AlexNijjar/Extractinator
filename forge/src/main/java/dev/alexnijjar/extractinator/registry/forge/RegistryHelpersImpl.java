package dev.alexnijjar.extractinator.registry.forge;

import com.mojang.datafixers.util.Pair;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.registry.RegistryHelpers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RegistryHelpersImpl {

    public static final Map<Registry<?>, DeferredRegister<?>> REGISTRIES = new HashMap<>();

    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(RegistryHelpers.BlockEntityFactory<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::create, blocks).build(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> DeferredRegister<T> getOrCreateRegistry(Registry<T> registry) {
        if(REGISTRIES.containsKey(registry)) return (DeferredRegister<T>) REGISTRIES.get(registry);
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registry.key(), Extractinator.MOD_ID);
        REGISTRIES.put(registry, deferredRegister);
        return deferredRegister;
    }

    public static <V, T extends V> Pair<Supplier<T>, ResourceLocation> registerFull(Registry<V> registry, String id, Supplier<T> object) {
        var registered = getOrCreateRegistry(registry).register(id, object);
        return Pair.of(registered, registered.getId());
    }
}
