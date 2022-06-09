package com.github.alexnijjar.the_extractinator.blocks.entity;

import java.util.ArrayList;
import java.util.List;

import com.github.alexnijjar.the_extractinator.TheExtractinator;
import com.github.alexnijjar.the_extractinator.registry.ModBlockEntities;
import com.github.alexnijjar.the_extractinator.registry.ModStats;
import com.github.alexnijjar.the_extractinator.util.BlockUtils;
import com.github.alexnijjar.the_extractinator.util.LootUtils;
import com.github.alexnijjar.the_extractinator.util.ModUtils;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ExtractinatorBlockEntity extends BlockEntity implements ExtractinatorInventory, SidedInventory {

    // slot one is for block input, e.g. silt and 24 additional slots are available for the output items.
    public static final int INVENTORY_SIZE = 24;
    protected final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1 + INVENTORY_SIZE, ItemStack.EMPTY);
    protected int transferCooldown;
    protected int remainingUsages;

    public ExtractinatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXTRACTINATOR_BLOCK_ENTITY, pos, state);
        int maximumUsages = TheExtractinator.CONFIG.extractinatorConfig.maximumUsages;
        remainingUsages = maximumUsages <= 0 ? -1 : maximumUsages;
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, ExtractinatorBlockEntity extractinator) {

        --extractinator.transferCooldown;

        // Destroy the Extractinator if it has run out of usages.
        if (extractinator.shouldDestroy()) {
            extractinator.destroyExtractinator();
        }

        // Extractinate a block from the inventory.
        if (extractinator.slotAvailable()) {
            extractinator.extractinateBlock();
        }

        // Drops the output items above the extractinator if no hopper or pipe is present.
        if (!extractinator.hasHopper() || !extractinator.hasPipe()) {
            extractinator.dropBlocks();
        }
    }

    public boolean shouldDestroy() {
        return this.remainingUsages == 0 && TheExtractinator.CONFIG.extractinatorConfig.maximumUsages <= 0;
    }

    public void destroyExtractinator() {
        world.breakBlock(this.pos, false);
        world.playSound(null, this.pos, SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1, 1);
    }

    // Checks if a hopper is below the extractinator.
    public boolean hasHopper() {
        BlockEntity belowBlock = world.getBlockEntity(pos.down());
        return belowBlock instanceof Hopper;
    }

    // Checks if there are available slots for storing extractinated items.
    public boolean slotAvailable() {
        for (int i = 1; i < this.getItems().size(); i++) {
            if (this.getItems().get(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void extractinateBlock() {
        BlockState aboveBlock = this.world.getBlockState(this.getPos().up());
        ItemStack input = this.getItems().get(0);

        // Places a block from the extractinator's inventory above the extractinator.
        if (aboveBlock.isAir() && !this.needsCooldown() && BlockUtils.inputSupported(input.getItem())) {

            Block inputBlock = Block.getBlockFromItem(input.getItem());
            input.decrement(1);
            BlockUtils.placeBlockSilently(world, this.getPos().up(), inputBlock);

            this.setCooldown(TheExtractinator.CONFIG.extractinatorConfig.inputCooldown);
            markDirty(world, this.getPos(), this.getCachedState());

            // Extracts the block above the extractinator if it is supported.
        } else if (BlockUtils.inputSupported(aboveBlock.getBlock().asItem())) {
            world.breakBlock(this.getPos().up(), false);
            if (this.remainingUsages > 0) {
                this.remainingUsages--;
            }
            // Generate loot for that block.
            List<ItemStack> items = LootUtils.extractMaterials(aboveBlock, world.random);

            // Update player stats.
            for (ServerPlayerEntity player : PlayerLookup.around((ServerWorld) world, this.getPos(), 32)) {
                player.increaseStat(ModStats.BLOCKS_EXTRACTINATED, 1);
            }

            // Add the loot into the inventory.
            for (ItemStack item : items) {
                for (int i = 1; i < this.getItems().size(); i++) {
                    ItemStack stack = this.getItems().get(i);
                    if (stack.isEmpty() || (stack.getItem().equals(item.getItem()) && stack.getCount() < stack.getMaxCount())) {
                        this.setStack(i, new ItemStack(item.getItem(), item.getCount() + stack.getCount()));
                        break;
                    }
                }
            }
        }
    }

    // check if an MI item pipe with either OUT or IN/OUT is connected.
    public boolean hasPipe() {
        if (ModUtils.modLoaded("modern_industrialization")) {
            for (Direction direction : Direction.values()) {
                BlockPos surroundingPos = this.getPos().offset(direction);
                BlockState surroundingState = world.getBlockState(surroundingPos);
                if (Registry.BLOCK.getId(surroundingState.getBlock()).equals(new Identifier("modern_industrialization", "pipe"))) {

                    BlockEntity surroundingEntity = world.getBlockEntity(surroundingPos);
                    BlockDataObject data = new BlockDataObject(surroundingEntity, surroundingPos);
                    NbtCompound nbt = data.getNbt().getCompound("pipe_data_0").getCompound(direction.getOpposite().asString().toLowerCase());
                    // "connections:2b" is OUT, "connections:1b" is IN/OUT.
                    if (nbt.toString().contains("connections:2b") || nbt.toString().contains("connections:1b")) {
                        return true;
                    }
                }
            }
        }
        return false;

        // TODO: Add Industrial Revolution item pipe support.
    }

    // Dispenses the output loot above the extractinator.
    public void dropBlocks() {
        List<Integer> slotsToClear = new ArrayList<>();

        for (int i = 1; i < this.getItems().size() - 1; i++) {
            ItemStack itemStack = this.getItems().get(i);
            if (itemStack.isEmpty()) {
                break;
            }

            int size = itemStack.getCount();
            itemStack.setCount((int) Math.ceil(size * TheExtractinator.CONFIG.extractinatorConfig.outputLootMultiplier));

            BlockPos pos = this.getPos();
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, itemStack);
            itemEntity.setVelocity(itemEntity.getVelocity().multiply(1.5f));
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
            slotsToClear.add(i);
        }

        // Clear all output slots after they've been dispensed.
        for (int slot : slotsToClear) {
            this.getItems().set(slot, ItemStack.EMPTY);
        }
    }

    public void setCooldown(int cooldown) {
        this.transferCooldown = cooldown;
    }

    public boolean needsCooldown() {
        return this.transferCooldown > 0;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("transferCooldown")) {
            this.transferCooldown = nbt.getInt("transferCooldown");
        }
        if (nbt.contains("remainingUsages")) {
            this.remainingUsages = nbt.getInt("remainingUsages");
        }
        Inventories.readNbt(nbt, getItems());
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("transferCooldown", transferCooldown);
        nbt.putInt("remainingUsages", remainingUsages);
        Inventories.writeNbt(nbt, this.getItems());
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
        return this.inventory;
    }
}