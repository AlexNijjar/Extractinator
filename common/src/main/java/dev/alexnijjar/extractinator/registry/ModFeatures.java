package dev.alexnijjar.extractinator.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import dev.alexnijjar.extractinator.Extractinator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class ModFeatures {
    public static final ResourcefulRegistry<Feature<?>> FEATURES = ResourcefulRegistries.create(BuiltInRegistries.FEATURE, Extractinator.MOD_ID);

    public static final RegistryEntry<Feature<OreConfiguration>> SILT = FEATURES.register("silt", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryEntry<Feature<OreConfiguration>> SLUSH = FEATURES.register("slush", () -> new OreFeature(OreConfiguration.CODEC));
}
