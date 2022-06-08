package com.github.alexnijjar.the_extractinator.blocks;

import com.github.alexnijjar.the_extractinator.blocks.entity.ExtractinatorBlockEntity;
import com.github.alexnijjar.the_extractinator.blocks.voxel.ExtractinatorBlockVoxel;
import com.github.alexnijjar.the_extractinator.registry.ModBlockEntities;
import com.github.alexnijjar.the_extractinator.util.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

@SuppressWarnings("deprecation")
public class ExtractinatorBlock extends BlockWithEntity implements Waterloggable {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public ExtractinatorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ExtractinatorBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, ModBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, ExtractinatorBlockEntity::serverTick);
    }

    // Drop inventory when extractinator is destroyed.
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ExtractinatorBlockEntity) {
                ItemScatterer.spawn(world, pos, (ExtractinatorBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // Inserts a block into the extractinator inventory.
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ItemStack mainHandItem = player.getMainHandStack();

            if (BlockUtils.inputSupported(mainHandItem.getItem())) {

                ExtractinatorBlockEntity entity = ((ExtractinatorBlockEntity) world.getBlockEntity(pos));
                if (entity != null) {
                    ItemStack current = entity.getStack(0);
                    if ((current.getItem().equals(mainHandItem.getItem()) || current.isEmpty()) && current.getCount() < current.getMaxCount()) {
                        entity.setStack(0, new ItemStack(mainHandItem.getItem(), current.getCount() + 1));
                        entity.setCooldown(0);
                        mainHandItem.decrement(1);
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }
        return ActionResult.CONSUME;
    }

    // Places a block above when a falling block, such as gravel, lands on the extractinator. This is required,
    // or else the gravel would behave like it was falling on a torch.
    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity instanceof FallingBlockEntity) {
            Block block = ((FallingBlockEntity) entity).getBlockState().getBlock();

            if (BlockUtils.inputSupported(block.asItem())) {
                ((FallingBlockEntity) entity).dropItem = false;
                BlockUtils.placeBlockSilently((World) world, entity.getBlockPos().up(), block);
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
        case NORTH -> ExtractinatorBlockVoxel.NORTH;
        case EAST -> ExtractinatorBlockVoxel.EAST;
        case SOUTH -> ExtractinatorBlockVoxel.SOUTH;
        case WEST -> ExtractinatorBlockVoxel.WEST;
        default -> null;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(WATERLOGGED, fluidState.getFluid().equals(Fluids.WATER));
    }
}
