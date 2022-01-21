package com.github.alexnijjar.the_extractinator.registry;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.WorldConfig;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

public class TEOres {

    public static ConfiguredFeature<?, ?> ORE_SILT_CONFIGURED = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreConfiguredFeatures.BASE_STONE_OVERWORLD,
                    TEBlocks.SILT.getDefaultState(),
                    TheExtractinator.CONFIG.worldConfig.siltVeinSize));

    public static PlacedFeature ORE_SILT_FEATURE = ORE_SILT_CONFIGURED.withPlacement(
            CountPlacementModifier.of(TheExtractinator.CONFIG.worldConfig.siltVeinsPerChunk), // number of veins per chunk
            SquarePlacementModifier.of(), // spreading horizontally
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(TheExtractinator.CONFIG.worldConfig.siltMaxSpawnHeight))); // height

    public static void register() {

        WorldConfig config = TheExtractinator.CONFIG.worldConfig;
        if (config.generateSiltOre) {
            Identifier siltId = new TEIdentifier("ore_silt");
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, siltId, ORE_SILT_CONFIGURED);
            Registry.register(BuiltinRegistries.PLACED_FEATURE, siltId, ORE_SILT_FEATURE);
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    RegistryKey.of(Registry.PLACED_FEATURE_KEY, siltId));
        }
    }
}
