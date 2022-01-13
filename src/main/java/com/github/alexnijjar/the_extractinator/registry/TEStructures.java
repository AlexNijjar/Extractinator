package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.structure.CabinStructure;
import com.github.alexnijjar.the_extractinator.structure.ConfiguredStructures;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.function.Predicate;

public final class TEStructures {

    public static StructureFeature<StructurePoolFeatureConfig> DEFAULT_CABIN = new CabinStructure(StructurePoolFeatureConfig.CODEC, "default");
    public static StructureFeature<StructurePoolFeatureConfig> DEEPSLATE_CABIN = new CabinStructure(StructurePoolFeatureConfig.CODEC, "deepslate");

    public static void register() {

        // Register structure.
        if (TheExtractinator.CONFIG.worldConfig.generateCabins) {
            FabricStructureBuilder.create(new TEIdentifier("default_underground_cabin"), DEFAULT_CABIN)
                    .step(GenerationStep.Feature.UNDERGROUND_STRUCTURES)
                    .defaultConfig(new StructureConfig(
                            TheExtractinator.CONFIG.worldConfig.cabinSpacing,
                            TheExtractinator.CONFIG.worldConfig.cabinSeparation,
                            951481806))
                    .adjustsSurface()
                    .register();
            FabricStructureBuilder.create(new TEIdentifier("deepslate_underground_cabin"), DEEPSLATE_CABIN)
                    .step(GenerationStep.Feature.UNDERGROUND_STRUCTURES)
                    .defaultConfig(new StructureConfig(
                            TheExtractinator.CONFIG.worldConfig.cabinSpacing,
                            TheExtractinator.CONFIG.worldConfig.cabinSeparation,
                            951481807))
                    .adjustsSurface()
                    .register();

            // Add structure spawning to biomes
            Predicate<BiomeSelectionContext> biomeSelector = BiomeSelectors.foundInOverworld();
            BiomeModifications.addStructure(
                    biomeSelector,
                    RegistryKey.of(
                            Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                            BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(ConfiguredStructures.DEFAULT_CONFIGURED_CABIN))
            );
            BiomeModifications.addStructure(
                    biomeSelector,
                    RegistryKey.of(
                            Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                            BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(ConfiguredStructures.DEEPSLATE_CONFIGURED_CABIN))
            );
        }
    }
}
