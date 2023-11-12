package tech.alexnijjar.extractinator.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import tech.alexnijjar.extractinator.Extractinator;

public class ExtractinatorFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Extractinator.init();
        addBiomeModifications();
    }

    public static void addBiomeModifications() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES,
            ResourceKey.create(Registries.PLACED_FEATURE,
                new ResourceLocation(Extractinator.MOD_ID, "silt")));
        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.ICY), GenerationStep.Decoration.UNDERGROUND_ORES,
            ResourceKey.create(Registries.PLACED_FEATURE,
                new ResourceLocation(Extractinator.MOD_ID, "slush")));
    }
}