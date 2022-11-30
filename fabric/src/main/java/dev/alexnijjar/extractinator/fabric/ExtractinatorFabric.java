package dev.alexnijjar.extractinator.fabric;

import dev.alexnijjar.extractinator.Extractinator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ExtractinatorFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Extractinator.init();
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                        new ResourceLocation(Extractinator.MOD_ID, "silt_ore")));
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.ICY), GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                        new ResourceLocation(Extractinator.MOD_ID, "slush_ore")));
    }
}