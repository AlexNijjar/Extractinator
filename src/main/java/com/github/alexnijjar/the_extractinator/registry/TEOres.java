package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.WorldConfig;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

import java.util.function.Predicate;

public class TEOres {

    public static void register() {

        WorldConfig config = TheExtractinator.CONFIG.worldConfig;

        if (config.generateSiltOre_v1) {
            ConfiguredFeature<?, ?> configured = createOreConfiguredFeature(TEBlocks.SILT, config.siltVeinSize_v2);
            PlacedFeature feature = createOreFeature(configured, config.siltVeinsPerChunk_v2, config.siltMinSpawnHeight_v1, config.siltMaxSpawnHeight_v1);

            registerOre(new TEIdentifier("ore_silt"), configured, feature, BiomeSelectors.foundInOverworld());
        }
        if (config.generateSlushOre_v1) {
            ConfiguredFeature<?, ?> configured = createOreConfiguredFeature(TEBlocks.SLUSH, config.slushVeinSize_v1);
            PlacedFeature feature = createOreFeature(configured, config.slushVeinsPerChunk_v1, config.slushMinSpawnHeight_v1, config.slushMaxSpawnHeight_v1);

            registerOre(new TEIdentifier("ore_slush"), configured, feature, BiomeSelectors.categories(Biome.Category.ICY));
        }

    }

    // 1.18.2 Preparation.

    public static void registerOre(Identifier id, ConfiguredFeature<?, ?> config, PlacedFeature feature, Predicate<BiomeSelectionContext> biomes) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, config);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id, feature);
        BiomeModifications.addFeature(
                biomes,
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, id)
        );
    }

    public static ConfiguredFeature<?, ?> createOreConfiguredFeature(Block block, int veinSize) {
        return new ConfiguredFeature<>(
                Feature.ORE,
                new OreFeatureConfig(
                        OreConfiguredFeatures.BASE_STONE_OVERWORLD,
                        block.getDefaultState(),
                        veinSize
                )
        );
    }

    public static PlacedFeature createOreFeature(ConfiguredFeature<?, ?> config, int veinsPerChunk, int min, int max) {
        return config.withPlacement(
                CountPlacementModifier.of(veinsPerChunk), // number of veins per chunk
                SquarePlacementModifier.of(), // spreading horizontally
                HeightRangePlacementModifier.uniform(YOffset.fixed(min), YOffset.fixed(max))
        );
    }
}
