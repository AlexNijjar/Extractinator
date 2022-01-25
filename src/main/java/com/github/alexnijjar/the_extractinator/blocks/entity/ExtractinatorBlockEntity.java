package com.github.alexnijjar.the_extractinator.blocks.entity;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.registry.TEBlockEntities;
import com.github.alexnijjar.the_extractinator.util.BlockUtils;
import com.github.alexnijjar.the_extractinator.util.LootUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExtractinatorBlockEntity extends BlockEntity implements ExtractinatorInventory, SidedInventory {

    private final DefaultedList<ItemStack> ITEMS = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int transferCooldown;

    public ExtractinatorBlockEntity(BlockPos pos, BlockState state) {
        super(TEBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, pos, state);
        this.transferCooldown = -1;
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, ExtractinatorBlockEntity blockEntity) {

        --blockEntity.transferCooldown;

        ItemStack input = blockEntity.ITEMS.get(0);
        BlockState aboveBlock = world.getBlockState(pos.up());

        if (aboveBlock.isAir()) {
            if (!blockEntity.needsCooldown()) {
                if (BlockUtils.inputSupported(input.getItem())) {

                    Block inputBlock = Block.getBlockFromItem(input.getItem());
                    BlockUtils.placeBlockSilently(world, pos.up(), inputBlock);

                    blockEntity.ITEMS.get(0).decrement(1);

                    blockEntity.setCooldown(TheExtractinator.CONFIG.extractinatorConfig.inputCooldown_v1);

                    markDirty(world, pos, state);
                }
            }
        } else if (BlockUtils.inputSupported(aboveBlock.getBlock().asItem())) {
            world.breakBlock(pos.up(), false);
            LootUtils.extractMaterials(aboveBlock, (ServerWorld) world, pos);
        }
    }

    private void setCooldown(int cooldown) {
        this.transferCooldown = cooldown;
    }

    private boolean needsCooldown() {
        return this.transferCooldown > 0;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, ITEMS);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, ITEMS);
        super.writeNbt(nbt);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return BlockUtils.inputSupported(stack.getItem());
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return ITEMS;
    }
}
