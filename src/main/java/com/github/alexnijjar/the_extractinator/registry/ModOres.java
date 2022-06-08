package com.github.alexnijjar.the_extractinator.registry;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.WorldConfig;
import com.github.alexnijjar.the_extractinator.util.ModIdentifier;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModOres {

    public static void register() {

        WorldConfig config = TheExtractinator.CONFIG.worldConfig;

        if (config.generateSiltOre_v1) {
            Identifier id = new ModIdentifier("ore_slush");
            ConfiguredFeature<?, ?> configured = createOreConfiguredFeature(id, ModBlocks.SILT, config.siltVeinSize_v2);
            PlacedFeature feature = createOreFeature(configured, config.siltVeinsPerChunk_v2, config.siltMinSpawnHeight_v1, config.siltMaxSpawnHeight_v1);

            registerOre(id, feature, BiomeSelectors.foundInOverworld());
        }
        if (config.generateSlushOre_v1) {
            Identifier id = new ModIdentifier("ore_silt");
            ConfiguredFeature<?, ?> configured = createOreConfiguredFeature(id, ModBlocks.SLUSH, config.slushVeinSize_v1);
            PlacedFeature feature = createOreFeature(configured, config.slushVeinsPerChunk_v1, config.slushMinSpawnHeight_v1, config.slushMaxSpawnHeight_v1);

            // TODO Add all cold biomes
            registerOre(id, feature, BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS));
        }
    }

    public static void registerOre(Identifier id, PlacedFeature feature, Predicate<BiomeSelectionContext> biomes) {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id, feature);
        BiomeModifications.addFeature(biomes, GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, id));
    }

    public static ConfiguredFeature<?, ?> createOreConfiguredFeature(Identifier id, Block block, int veinSize) {
        ConfiguredFeature<?, ?> configured = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, block.getDefaultState(), veinSize));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configured);
        return configured;
    }

    public static PlacedFeature createOreFeature(ConfiguredFeature<?, ?> configured, int veinsPerChunk, int min, int max) {

        List<PlacementModifier> placementModifiers = Arrays.asList(CountPlacementModifier.of(veinsPerChunk), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(min), YOffset.fixed(max)));

        return new PlacedFeature(getEntry(BuiltinRegistries.CONFIGURED_FEATURE, configured), placementModifiers);
    }

    public static <T> RegistryEntry<T> getEntry(Registry<T> registry, T value) {
        return registry.getEntry(registry.getKey(value).orElseThrow()).orElseThrow();
    }
}