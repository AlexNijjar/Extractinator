package com.github.alexnijjar.the_extractinator.structure;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.registry.TEStructures;
import com.github.alexnijjar.the_extractinator.structure.generator.BasicCabinGenerator;
import com.github.alexnijjar.the_extractinator.structure.generator.DeepslateCabinGenerator;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class ConfiguredStructures {

    public static ConfiguredStructureFeature<?, ?> DEFAULT_CONFIGURED_CABIN = TEStructures.DEFAULT_CABIN
            .configure(new StructurePoolFeatureConfig(() -> BasicCabinGenerator.STARTING_POOL, 1));

    public static ConfiguredStructureFeature<?, ?> DEEPSLATE_CONFIGURED_CABIN = TEStructures.DEEPSLATE_CABIN
            .configure(new StructurePoolFeatureConfig(() -> DeepslateCabinGenerator.STARTING_POOL, 1));

    public static void register() {

        if (TheExtractinator.CONFIG.worldConfig.generateCabins) {
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_cabin"), DEFAULT_CONFIGURED_CABIN);
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_deepslate_cabin"), DEEPSLATE_CONFIGURED_CABIN);
        }
    }

    /*

tp ~1000 ~ ~1000
effect give @p night_vision 1000000
fill ~ ~ ~ ~10 ~10 ~10 air

setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_bedroom"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_blocks"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_enchanting"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_enhanced_enchanting"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_enhanced_metalworks"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_kitchen"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_metalworks"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_music"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_potion"}
     */
}
