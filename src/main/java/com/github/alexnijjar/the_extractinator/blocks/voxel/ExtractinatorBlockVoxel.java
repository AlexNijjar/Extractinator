package com.github.alexnijjar.the_extractinator.blocks.voxel;

import net.minecraft.block.Block;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.stream.Stream;

public class ExtractinatorBlockVoxel {

    // Made with the Mod Utils Blockbench plugin.
    public static final VoxelShape NORTH = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 1, 16),
            Block.createCuboidShape(1, 1, 1, 6, 7, 16),
            Block.createCuboidShape(1, 7, 2, 7, 11, 14),
            Block.createCuboidShape(6, 1, 0, 16, 2, 16),
            Block.createCuboidShape(6, 6, 0, 16, 7, 1),
            Block.createCuboidShape(6, 6, 15, 16, 7, 16),
            Block.createCuboidShape(15, 6, 1, 16, 7, 15),
            Block.createCuboidShape(6, 6, 1, 7, 7, 15),
            Block.createCuboidShape(7, 2, 15, 15, 6, 15.2),
            Block.createCuboidShape(15, 2, 0.8, 15.2, 6, 15.2),
            Block.createCuboidShape(6.8, 2, 11, 7, 6, 15.2),
            Block.createCuboidShape(6.8, 2, 0.8, 7, 6, 5),
            Block.createCuboidShape(6, 2, 5, 7, 6, 11),
            Block.createCuboidShape(7, 2, 0.8, 15, 6, 1),
            Block.createCuboidShape(9, 2, 0.6, 10, 6, 0.8),
            Block.createCuboidShape(0, 1, 0, 1, 6, 16),
            Block.createCuboidShape(0, 6, 7, 1, 11, 9),
            Block.createCuboidShape(0, 11, 6, 3, 13, 10),
            Block.createCuboidShape(1, 13, 7, 2, 14, 9),
            Block.createCuboidShape(4, 11, 6, 8, 13, 10),
            Block.createCuboidShape(5, 13, 7, 8, 14, 9),
            Block.createCuboidShape(6, 14, 7, 8, 15, 9),
            Block.createCuboidShape(7, 9, 6, 8, 11, 10),
            Block.createCuboidShape(4, 11, 5, 8, 12, 6),
            Block.createCuboidShape(3.8, 11, 5, 4, 12.2, 7),
            Block.createCuboidShape(4, 12, 5, 7, 12.2, 6),
            Block.createCuboidShape(3, 11, 7, 4, 12.2, 9),
            Block.createCuboidShape(8, 6, 5, 13, 15, 11),
            Block.createCuboidShape(7, 2, 1, 15, 6, 15),
            Block.createCuboidShape(2, 7, 14, 6, 9, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 1, 16),
            Block.createCuboidShape(0, 1, 1, 15, 7, 6),
            Block.createCuboidShape(2, 7, 1, 14, 11, 7),
            Block.createCuboidShape(0, 1, 6, 16, 2, 16),
            Block.createCuboidShape(15, 6, 6, 16, 7, 16),
            Block.createCuboidShape(0, 6, 6, 1, 7, 16),
            Block.createCuboidShape(1, 6, 15, 15, 7, 16),
            Block.createCuboidShape(1, 6, 6, 15, 7, 7),
            Block.createCuboidShape(0.8, 2, 7, 1, 6, 15),
            Block.createCuboidShape(0.8, 2, 15, 15.2, 6, 15.2),
            Block.createCuboidShape(0.8, 2, 6.8, 5, 6, 7),
            Block.createCuboidShape(11, 2, 6.8, 15.2, 6, 7),
            Block.createCuboidShape(5, 2, 6, 11, 6, 7),
            Block.createCuboidShape(15, 2, 7, 15.2, 6, 15),
            Block.createCuboidShape(15.2, 2, 9, 15.4, 6, 10),
            Block.createCuboidShape(0, 1, 0, 16, 6, 1),
            Block.createCuboidShape(7, 6, 0, 9, 11, 1),
            Block.createCuboidShape(6, 11, 0, 10, 13, 3),
            Block.createCuboidShape(7, 13, 1, 9, 14, 2),
            Block.createCuboidShape(6, 11, 4, 10, 13, 8),
            Block.createCuboidShape(7, 13, 5, 9, 14, 8),
            Block.createCuboidShape(7, 14, 6, 9, 15, 8),
            Block.createCuboidShape(6, 9, 7, 10, 11, 8),
            Block.createCuboidShape(10, 11, 4, 11, 12, 8),
            Block.createCuboidShape(9, 11, 3.8, 11, 12.2, 4),
            Block.createCuboidShape(10, 12, 4, 11, 12.2, 7),
            Block.createCuboidShape(7, 11, 3, 9, 12.2, 4),
            Block.createCuboidShape(5, 6, 8, 11, 15, 13),
            Block.createCuboidShape(1, 2, 7, 15, 6, 15),
            Block.createCuboidShape(1, 7, 2, 2, 9, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 1, 16),
            Block.createCuboidShape(10, 1, 0, 15, 7, 15),
            Block.createCuboidShape(9, 7, 2, 15, 11, 14),
            Block.createCuboidShape(0, 1, 0, 10, 2, 16),
            Block.createCuboidShape(0, 6, 15, 10, 7, 16),
            Block.createCuboidShape(0, 6, 0, 10, 7, 1),
            Block.createCuboidShape(0, 6, 1, 1, 7, 15),
            Block.createCuboidShape(9, 6, 1, 10, 7, 15),
            Block.createCuboidShape(1, 2, 0.8, 9, 6, 1),
            Block.createCuboidShape(0.8, 2, 0.8, 1, 6, 15.2),
            Block.createCuboidShape(9, 2, 0.8, 9.2, 6, 5),
            Block.createCuboidShape(9, 2, 11, 9.2, 6, 15.2),
            Block.createCuboidShape(9, 2, 5, 10, 6, 11),
            Block.createCuboidShape(1, 2, 15, 9, 6, 15.2),
            Block.createCuboidShape(6, 2, 15.2, 7, 6, 15.4),
            Block.createCuboidShape(15, 1, 0, 16, 6, 16),
            Block.createCuboidShape(15, 6, 7, 16, 11, 9),
            Block.createCuboidShape(13, 11, 6, 16, 13, 10),
            Block.createCuboidShape(14, 13, 7, 15, 14, 9),
            Block.createCuboidShape(8, 11, 6, 12, 13, 10),
            Block.createCuboidShape(8, 13, 7, 11, 14, 9),
            Block.createCuboidShape(8, 14, 7, 10, 15, 9),
            Block.createCuboidShape(8, 9, 6, 9, 11, 10),
            Block.createCuboidShape(8, 11, 10, 12, 12, 11),
            Block.createCuboidShape(12, 11, 9, 12.2, 12.2, 11),
            Block.createCuboidShape(9, 12, 10, 12, 12.2, 11),
            Block.createCuboidShape(12, 11, 7, 13, 12.2, 9),
            Block.createCuboidShape(3, 6, 5, 8, 15, 11),
            Block.createCuboidShape(1, 2, 1, 9, 6, 15),
            Block.createCuboidShape(10, 7, 1, 14, 9, 2)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 1, 16),
            Block.createCuboidShape(1, 1, 10, 16, 7, 15),
            Block.createCuboidShape(2, 7, 9, 14, 11, 15),
            Block.createCuboidShape(0, 1, 0, 16, 2, 10),
            Block.createCuboidShape(0, 6, 0, 1, 7, 10),
            Block.createCuboidShape(15, 6, 0, 16, 7, 10),
            Block.createCuboidShape(1, 6, 0, 15, 7, 1),
            Block.createCuboidShape(1, 6, 9, 15, 7, 10),
            Block.createCuboidShape(15, 2, 1, 15.2, 6, 9),
            Block.createCuboidShape(0.8, 2, 0.8, 15.2, 6, 1),
            Block.createCuboidShape(11, 2, 9, 15.2, 6, 9.2),
            Block.createCuboidShape(0.8, 2, 9, 5, 6, 9.2),
            Block.createCuboidShape(5, 2, 9, 11, 6, 10),
            Block.createCuboidShape(0.8, 2, 1, 1, 6, 9),
            Block.createCuboidShape(0.6, 2, 6, 0.8, 6, 7),
            Block.createCuboidShape(0, 1, 15, 16, 6, 16),
            Block.createCuboidShape(7, 6, 15, 9, 11, 16),
            Block.createCuboidShape(6, 11, 13, 10, 13, 16),
            Block.createCuboidShape(7, 13, 14, 9, 14, 15),
            Block.createCuboidShape(6, 11, 8, 10, 13, 12),
            Block.createCuboidShape(7, 13, 8, 9, 14, 11),
            Block.createCuboidShape(7, 14, 8, 9, 15, 10),
            Block.createCuboidShape(6, 9, 8, 10, 11, 9),
            Block.createCuboidShape(5, 11, 8, 6, 12, 12),
            Block.createCuboidShape(5, 11, 12, 7, 12.2, 12.2),
            Block.createCuboidShape(5, 12, 9, 6, 12.2, 12),
            Block.createCuboidShape(7, 11, 12, 9, 12.2, 13),
            Block.createCuboidShape(5, 6, 3, 11, 15, 8),
            Block.createCuboidShape(1, 2, 1, 15, 6, 9),
            Block.createCuboidShape(14, 7, 10, 15, 9, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
}
