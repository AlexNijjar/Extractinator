package dev.alexnijjar.extractinator.blocks;

import dev.alexnijjar.extractinator.registry.ModBlockEntities;
import dev.alexnijjar.extractinator.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ExtractinatorBlockEntity extends BlockEntity implements ExtractinatorContainer {
    private final NonNullList<ItemStack> inventory;

    public ExtractinatorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.EXTRACTINATOR.get(), blockPos, blockState);
        inventory = NonNullList.withSize(33, ItemStack.EMPTY);
    }

    public void tick() {
        if (!this.getBlockLevel().isClientSide()) {
            if (getBlockLevel().getGameTime() % 8 == 0) {
                extractinate();
            }
        }
    }

    protected void extractinate() {
        dispenseItems();
        placeBlockAbove();
        extractBlockAbove();
        setChanged();
    }

    protected void placeBlockAbove() {
        BlockState above = level.getBlockState(this.getBlockPos().above());
        ItemStack input = inventory.get(0);
        Block toPlace = Block.byItem(input.getItem());
        if (!(toPlace == Blocks.AIR)) {
            if (above.isAir() || Blocks.WATER.equals(above.getBlock())) {
                level.setBlock(this.getBlockPos().above(), toPlace.defaultBlockState(), Block.UPDATE_NONE);
            } else {
                level.playSound(null, this.getBlockPos(), SoundEvents.GRAVEL_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
                List<ItemStack> outputs = ModUtils.extractItem(this.getBlockLevel(), input);
                if (!outputs.isEmpty()) {
                    outputs.forEach(this::addItem);
                }
            }
            getItem(0).shrink(1);
        }
    }

    protected void extractBlockAbove() {
        BlockState above = level.getBlockState(this.getBlockPos().above());
        if (above.isAir()) return;
        ItemStack stack = above.getBlock().asItem().getDefaultInstance();
        if (ModUtils.isValidInput(getBlockLevel(), stack)) {
            this.getBlockLevel().destroyBlock(this.getBlockPos().above(), false);
            List<ItemStack> outputs = ModUtils.extractItem(this.getBlockLevel(), stack);
            if (!outputs.isEmpty()) {
                outputs.forEach(this::addItem);
            }
        }
    }

    protected void dispenseItems() {
        for (int i = 1; i < getInventory().size(); i++) {
            ItemStack stack = this.getItem(i);
            if (stack.isEmpty()) continue;
            if (!getBlockLevel().getBlockState(getBlockPos().above()).isAir()) continue;
            BlockPos pos = this.getBlockPos();
            ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5f, pos.getY() + 2.0f, pos.getZ() + 0.5f, stack.copy());
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().scale(1.5f));
            stack.setCount(0);
            level.addFreshEntity(itemEntity);
            break;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        ContainerHelper.saveAllItems(tag, this.inventory);
    }

    @Override
    public void load(CompoundTag tag) {
        ContainerHelper.loadAllItems(tag, this.inventory);
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return this.inventory;
    }

    @Override
    public Level getBlockLevel() {
        return this.getLevel();
    }
}