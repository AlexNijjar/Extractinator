package com.github.alexnijjar.the_extractinator.structure.generator;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.WorldConfig;
import com.github.alexnijjar.the_extractinator.util.TEIdentifier;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;

public class IceCabinGenerator {

    public static final StructurePool STARTING_POOL;

    static {
        WorldConfig config = TheExtractinator.CONFIG.worldConfig;
        STARTING_POOL = StructurePools.register(
                new StructurePool(
                        new TEIdentifier("ice_cabin_base"),
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_base_1"), config.intactCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_upper_base_1"), config.intactCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_lower_base_1"), config.intactCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_base_2"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_upper_base_2"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_lower_base_2"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_base_3"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_upper_base_3"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_lower_base_3"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_base_4"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_upper_base_4"), config.brokenCabinWeight_v1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_lower_base_4"), config.brokenCabinWeight_v1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePools.register(
                new StructurePool(
                        new TEIdentifier("ice_intact_cabin_top"),
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_top_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePools.register(
                new StructurePool(
                        new TEIdentifier("ice_intact_cabin_bottom"),
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_bottom_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePools.register(
                new StructurePool(
                        new TEIdentifier("ice_cabin_top"),
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_top_2"), 1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_top_3"), 1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_top_4"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );

        StructurePools.register(
                new StructurePool(
                        new TEIdentifier("ice_cabin_bottom"),
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_bottom_2"), 1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_bottom_3"), 1),
                                new Pair<>(StructurePoolElement.ofSingle(TheExtractinator.MOD_ID + ":cabin/ice/cabin_bottom_4"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
    }
}
