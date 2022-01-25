package com.github.alexnijjar.the_extractinator.structure;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.config.WorldConfig;
import com.mojang.serialization.Codec;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.PostPlacementProcessor;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;
import java.util.Random;

public class CabinStructure extends StructureFeature<StructurePoolFeatureConfig> {

    public CabinStructure(Codec<StructurePoolFeatureConfig> codec, String type) {
        super(codec, (context) -> CabinStructure.generate(context, type), PostPlacementProcessor.EMPTY);
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> generate(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context, String type) {

        WorldConfig config = TheExtractinator.CONFIG.worldConfig;
        int min;
        int max;

        switch (type) {
            case "deepslate" -> {
                min = config.deepslateCabinMinHeight_v1;
                max = config.deepslateCabinMaxHeight_v1;
            }
            case "ocean" -> {
                min = config.oceanCabinMinHeight_v1;
                max = config.oceanCabinMaxHeight_v1;
            }
            default -> {
                min = config.cabinMinHeight_v1;
                max = config.cabinMaxHeight_v1;
            }
        }

        Random random = new Random();
        int randomY = random.nextInt(max - min) + min;
        BlockPos pos = context.chunkPos().getCenterAtY(randomY);

        return StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, pos, false, false);
    }
}
