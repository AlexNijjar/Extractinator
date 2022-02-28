package com.github.alexnijjar.the_extractinator.blocks.entity;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.registry.TEBlockEntities;
import com.github.alexnijjar.the_extractinator.util.BlockUtils;
import com.github.alexnijjar.the_extractinator.util.LootUtils;
import com.github.alexnijjar.the_extractinator.util.SupportedMods;
import com.github.alexnijjar.the_extractinator.util.TEUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.Hopper;
import net.minecraft.command.BlockDataObject;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ExtractinatorBlockEntity extends BlockEntity implements ExtractinatorInventory, SidedInventory {

    // slot one is for block input, e.g. silt and 24 additional slots are available for the output items.
    private final int INVENTORY_SIZE = 24;
    private final DefaultedList<ItemStack> ITEMS = DefaultedList.ofSize(1 + INVENTORY_SIZE, ItemStack.EMPTY);
    private int transferCooldown;

    public ExtractinatorBlockEntity(BlockPos pos, BlockState state) {
        super(TEBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, pos, state);
    }


    public static void serverTick(World world, BlockPos pos, BlockState state, ExtractinatorBlockEntity blockEntity) {

        --blockEntity.transferCooldown;

        BlockState aboveBlock = world.getBlockState(pos.up());
        ItemStack input = blockEntity.ITEMS.get(0);

        boolean slotAvailable = false;

        // Checks if there are available slots for storing extractinated items.
        for (int i = 1; i < blockEntity.ITEMS.size(); i++) {
            if (blockEntity.ITEMS.get(i).isEmpty()) {
                slotAvailable = true;
                break;
            }
        }

        if (slotAvailable) {

            // Places a block from the extractinator's inventory above the extractinator.
            if (aboveBlock.isAir() && !blockEntity.needsCooldown() && BlockUtils.inputSupported(input.getItem())) {

                Block inputBlock = Block.getBlockFromItem(input.getItem());
                input.decrement(1);
                BlockUtils.placeBlockSilently(world, pos.up(), inputBlock);

                blockEntity.setCooldown(TheExtractinator.CONFIG.extractinatorConfig.inputCooldown_v1);
                markDirty(world, pos, state);

                // Extracts the block above the extractinator if it is supported.
            } else if (BlockUtils.inputSupported(aboveBlock.getBlock().asItem())) {
                world.breakBlock(pos.up(), false);
                // Generate loot for that block.
                List<ItemStack> items = (LootUtils.extractMaterials(aboveBlock, (ServerWorld) world, pos));

                // Add the loot into the inventory.
                for (ItemStack item : items) {
                    for (int i = 1; i < blockEntity.ITEMS.size(); i++) {
                        ItemStack stack = blockEntity.ITEMS.get(i);
                        if (stack.isEmpty() || (stack.getItem().equals(item.getItem()) && stack.getCount() < stack.getMaxCount())) {
                            blockEntity.setStack(i, new ItemStack(item.getItem(), item.getCount() + stack.getCount()));
                            break;
                        }
                    }
                }
            }
        }


        // Checks if a hopper is below the extractinator.
        BlockEntity belowBlock = world.getBlockEntity(pos.down());
        boolean dropBlocks = true;

        if (belowBlock != null) {
            dropBlocks = !(belowBlock instanceof Hopper);

            if (dropBlocks) {

                // If a hopper is not present, check if an MI item pipe with either OUT or IN/OUT is connected.
                if (TEUtils.checkMod(SupportedMods.MODERN_INDUSTRIALIZATION)) {
                    for (Direction direction : Direction.values()) {
                        BlockPos surroundingPos = pos.offset(direction);
                        BlockState surroundingState = world.getBlockState(surroundingPos);
                        if (Registry.BLOCK.getId(surroundingState.getBlock()).equals(new Identifier(TEUtils.modToModId(SupportedMods.MODERN_INDUSTRIALIZATION), "pipe"))) {

                            BlockEntity surroundingEntity = world.getBlockEntity(surroundingPos);
                            BlockDataObject data = new BlockDataObject(surroundingEntity, surroundingPos);
                            NbtCompound nbt = data.getNbt().getCompound("pipe_data_0").getCompound(direction.getOpposite().asString().toLowerCase());
                            // "connections:2b" is OUT, "connections:1b" is IN/OUT.
                            if (nbt.toString().contains("connections:2b") || nbt.toString().contains("connections:1b")) {
                                dropBlocks = false;
                                break;
                            }
                        }
                    }
                }

                // TODO: Add Industrial Revolution item pipe support.
            }
        }

        // Drops the output items above the extractinator if no hopper or MI item pipe is present.
        if (dropBlocks) {

            List<Integer> slotsToClear = new ArrayList<>();

            for (int i = 1; i < blockEntity.ITEMS.size() - 1; i++) {
                ItemStack itemStack = blockEntity.ITEMS.get(i);
                if (itemStack.isEmpty()) break;

                int size = itemStack.getCount();
                itemStack.setCount((int) Math.ceil(size * TheExtractinator.CONFIG.extractinatorConfig.outputLootMultiplier_v1));

                ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, itemStack);
                itemEntity.setVelocity(itemEntity.getVelocity().multiply(1.5f));
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
                slotsToClear.add(i);
            }

            // Clear all output slots after they've been dispensed.
            for (int slot : slotsToClear)
                blockEntity.ITEMS.set(slot, ItemStack.EMPTY);
        }

    }


    public void setCooldown(int cooldown) {
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
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return BlockUtils.inputSupported(stack.getItem()) && slot == 0;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot != 0;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return ITEMS;
    }
}
