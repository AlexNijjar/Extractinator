package com.github.alexnijjar.the_extractinator.structure;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.registry.TEStructures;
import com.github.alexnijjar.the_extractinator.structure.generator.*;
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

    public static ConfiguredStructureFeature<?, ?> OCEAN_CONFIGURED_CABIN = TEStructures.OCEAN_CABIN
            .configure(new StructurePoolFeatureConfig(() -> OceanCabinGenerator.STARTING_POOL, 1));

    public static ConfiguredStructureFeature<?, ?> JUNGLE_CONFIGURED_CABIN = TEStructures.JUNGLE_CABIN
            .configure(new StructurePoolFeatureConfig(() -> JungleCabinGenerator.STARTING_POOL, 1));

    public static ConfiguredStructureFeature<?, ?> DESERT_CONFIGURED_CABIN = TEStructures.DESERT_CABIN
            .configure(new StructurePoolFeatureConfig(() -> DesertCabinGenerator.STARTING_POOL, 1));

    public static ConfiguredStructureFeature<?, ?> ICE_CONFIGURED_CABIN = TEStructures.ICE_CABIN
            .configure(new StructurePoolFeatureConfig(() -> IceCabinGenerator.STARTING_POOL, 1));

    public static void register() {
        if (TheExtractinator.CONFIG.worldConfig.generateCabins_v1) {
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_cabin"), DEFAULT_CONFIGURED_CABIN);
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_deepslate_cabin"), DEEPSLATE_CONFIGURED_CABIN);
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_ocean_cabin"), OCEAN_CONFIGURED_CABIN);
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_jungle_cabin"), JUNGLE_CONFIGURED_CABIN);
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_desert_cabin"), DESERT_CONFIGURED_CABIN);
            Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new TEIdentifier("configured_ice_cabin"), ICE_CONFIGURED_CABIN);
        }
    }


    /*
fill ~ ~ ~ ~10 ~10 ~10 air
fill ~ ~ ~ ~15 ~15 ~15 air
fill ~ ~ ~ ~10 ~10 ~10 water replace air

setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_bedroom"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_blocks"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_enchanting"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_enhanced_enchanting"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_enhanced_metalworks"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_kitchen"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_metalworks"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_music"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_potion"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_beekeeping"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_fletching"}
setblock ~ ~ ~ minecraft:chest{LootTable:"the_extractinator:chests/cabin_weaving"}

setblock ~ ~ ~ minecraft:chest{LootTable:"minecraft:chests/jungle_temple"}
setblock ~ ~ ~ minecraft:chest{LootTable:"minecraft:chests/dungeon"}
     */
}
